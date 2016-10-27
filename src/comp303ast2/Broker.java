package comp303ast2;

public abstract class Broker implements Runnable {
	int brokerID;
	Plane plane;
	
	public Broker(Plane plane, int brokerID){
		this.plane = plane;
		this.brokerID = brokerID;
	}
	
	protected boolean attemptReservation(int column, int row, int brokerID){
		return plane.requestSeat(column, row, brokerID);
	}
	
	protected boolean cancelReservation(int row, int column, int brokerID){
		return plane.freeSeat(column, row, brokerID);
	}
}
