package comp303ast2;

public class Application {

	
	public static void main(String args[]){
		
		
		//Not using a Broker interface since there are no useful public method
		Plane plane = new Plane();
		Broker broker1 = new AutonomeBroker(plane,1);
		Broker broker2 = new AutonomeBroker(plane,2);
		Broker broker3 = new ManualBroker(plane,3);
		
		Thread t1 = new Thread(broker1);
		Thread t2 = new Thread(broker2);
		Thread t3 = new Thread(broker3);
		
		//t1.start();
		//t2.start();
		t3.start();
		
	}
}
