package comp303ast2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Plane {
	private Lock seatsLock = new ReentrantLock();
	private Lock hasChangedLock = new ReentrantLock();
	private Condition hasChangedCondition = hasChangedLock.newCondition();
	private boolean hasChanged;
	private int seatsTaken = 0;
	private int seats[][] = new int[50][4];
	
	public boolean requestSeat(int column, int row, int broker){
		if(seats[row][column]!=0){
			return false;
		}
		System.out.println("Broker "+broker+" reserved seat "+column+"-"+row);
		seatsLock.lock();
		try{
			seats[row][column] = broker;
			seatsTaken++;
		}finally{
			seatsLock.unlock();
		}
		
		if(!hasChanged){	//Do not request the lock if we dont need to
			hasChangedLock.lock();
			hasChanged = true;
			hasChangedCondition.signalAll();
			hasChangedLock.unlock();
		}
		
		return true;
	}
	
	//Free seats if called by creator, else return false. Return false if already free
	public boolean freeSeat(int column, int row, int broker){
		if(seats[row][column] == 9 || seats[row][column] != broker){
			return false;
		}
		try{
			seats[row][column] = 0;
			seatsTaken--;
		}finally{
			seatsLock.unlock();
		}
		
		
		if(!hasChanged){	//Do not request the lock if we don't need to
			hasChangedLock.lock();
			hasChanged = true;
			hasChangedCondition.signalAll();
			hasChangedLock.unlock();
		}
		
		return true;
	}
	
	public int getSeatStatus(int column, int row){
		return seats[row][column];
	}
	
	public boolean isFull(){
		if(seatsTaken == 200){
			return true;
		}
		return false;
	}
	
	public boolean needUpdate(){
		return hasChanged;
	}
	
	public void setUpdateState(){
		hasChanged = false;
	}
	
	public void waitUpdate() throws InterruptedException{
		try{
			hasChangedLock.lock();
			hasChangedCondition.await();
		}finally{
			hasChangedLock.unlock();
		}
	}
	
}
