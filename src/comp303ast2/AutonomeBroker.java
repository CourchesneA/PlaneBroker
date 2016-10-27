package comp303ast2;

import java.util.concurrent.ThreadLocalRandom;

public class AutonomeBroker extends Broker {
	//private Plane plane;
	//private int brokerID;
	
	public AutonomeBroker(Plane plane, int brokerID){
		super(plane, brokerID);
	}
	
	@Override
	public void run() {
		
		while(!plane.isFull()){
			
			double waitTime = ThreadLocalRandom.current().nextInt(500,4000);	//Time before next random attempt: 1-10
			try {
				Thread.sleep((long) waitTime);
				int column = (ThreadLocalRandom.current().nextInt(0,4));
				int row = ThreadLocalRandom.current().nextInt(0,50);
				System.out.println("Random: "+column+"-"+row+" wait: "+waitTime);
				attemptReservation(column,row,brokerID);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		System.exit(0);		//Exit when there is no more free seat
		
	}
	
}
