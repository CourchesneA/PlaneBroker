package comp303ast2;

public class AutonomeBroker extends Broker {
	//private Plane plane;
	//private int brokerID;
	
	public AutonomeBroker(Plane plane, int brokerID){
		super(plane, brokerID);
	}
	
	@Override
	public void run() {
		
		while(!plane.isFull()){
			
			double waitTime = Math.random()*10;	//Time before next random attempt: 1-10
			try {
				int row = (int) Math.random()*4 + 1;
				int column = (int) Math.random()*50 + 1;
				attemptReservation(row,column,brokerID);
				Thread.sleep((long) waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		System.exit(0);		//Exit when there is no more free seat
		
	}
	
}
