package comp303ast2;

public abstract class Broker implements Runnable {
	int brokerID;
	Plane plane;
	
	public Broker(Plane plane, int brokerID){
		this.plane = plane;
		this.brokerID = brokerID;
	}
	
	protected boolean attemptReservation(int row, int column, int brokerID){
		boolean result;
		
		plane.seatsLock.lock();
		try{
			result = plane.requestSeat(column, row, brokerID);
		}finally{
			plane.seatsLock.unlock();
		}
		return result;
	}
	
	protected boolean cancelReservation(int row, int column, int brokerID){
		boolean result;
		
		plane.seatsLock.lock();
		try{
			result = plane.freeSeat(column, row, brokerID);
		}finally{
			plane.seatsLock.unlock();
		}
		return result;
	}
}
