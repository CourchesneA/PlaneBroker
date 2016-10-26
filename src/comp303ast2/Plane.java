package comp303ast2;

import java.util.concurrent.Semaphore;

public class Plane {
	public Semaphore mutex = new Semaphore(1);
	
	private int seats[][] = new int[50][4];
	
	public boolean requestSeat(int column, int row, int broker){
		if(seats[row][column]!=0){
			return false;
		}
		seats[row][column] = broker;
		return true;
	}
	
	//Free seats if called by creator, else return false. Return false if already free
	public boolean freeSeat(int column, int row, int broker){
		if(seats[row][column] == 9 || seats[row][column] != broker){
			return false;
		}
		
		seats[row][column] = 0;
		return true;
	}
	
}
