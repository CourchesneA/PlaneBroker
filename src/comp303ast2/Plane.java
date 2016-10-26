package comp303ast2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Plane {
	public Lock seatsLock = new ReentrantLock();
	public Lock asChangedLock = new ReentrantLock();
	/*private Condition asChangedCondition = asChangedLock.newCondition();
	private boolean asChanged = false;*/
	private int seatsTaken = 0;
	private int seats[][] = new int[4][50];
	
	public boolean requestSeat(int column, int row, int broker){
		if(seats[row][column]!=0){
			return false;
		}
		seats[row][column] = broker;
		seatsTaken++;
		
		/*asChangedLock.lock();
		asChanged = true;
		asChangedCondition.signalAll();
		asChangedLock.unlock();*/
		
		return true;
	}
	
	//Free seats if called by creator, else return false. Return false if already free
	public boolean freeSeat(int column, int row, int broker){
		if(seats[row][column] == 9 || seats[row][column] != broker){
			return false;
		}
		
		seats[row][column] = 0;
		
	/*	asChangedLock.lock();
		asChanged = true;
		asChangedCondition.signalAll();
		asChangedLock.unlock();*/
		
		return true;
	}
	
	public boolean isFull(){
		if(seatsTaken == 200){
			return true;
		}
		return false;
	}
	
	
}
