package comp303ast2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ManualBroker extends Broker {
	JButton[] buttonArray = new JButton[200];

	public ManualBroker(Plane plane, int brokerID){
		super(plane,brokerID);
	}
	
	@Override
	public void run() {
		try{
			JFrame frame = new JFrame();
			frame.setLayout(new GridLayout(0,4,5,5));
			
			createButtons(200,frame);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			while(!plane.isFull()){
				if(!plane.needUpdate()){
					plane.waitUpdate();
				}
				updateDisplay();
				Thread.sleep(500);
			}
			
		}catch( InterruptedException e){
			System.out.println("The thread has been interrupted");
		}

	}
	
	private void createButtons(int seatNumber, JFrame frame){
		for(int i = 0; i<seatNumber; i++){
			String buttonName = "seat "+(i+1);
			JButton button = new JButton(buttonName);
			button.addActionListener(createReserveButtonListener(i, brokerID));
			buttonArray[i] = button;

			frame.add(buttonArray[i]);
			
			
		}
	}
	
	private ActionListener createReserveButtonListener(final int index, final int brokerID){
		return new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(attemptReservation(index%4, index/4, brokerID)){
					JButton currentButton = buttonArray[index];
					currentButton.setBackground(Color.blue);
					currentButton.setText(Integer.toString(brokerID));
					currentButton.setForeground(Color.white);
				}
			}
		};
	}
	
	private void updateDisplay(){
		
		for(int i=0; i<200; i++){
			int status;
			if( (status = plane.getSeatStatus(i%4,i/4)) != 0){
				buttonArray[i].setText(Integer.toString(status));
				switch(status){
					case 1:
						buttonArray[i].setBackground(Color.red);
						break;
					case 2:
						buttonArray[i].setBackground(Color.green);
						break;
					case 3:
						buttonArray[i].setBackground(Color.blue);
						buttonArray[i].setForeground(Color.white);
						break;
				}
			}
		}
	}
	
	
	
	/*
	private ActionListener createRefundButtonListener(final int index, final int brokerID){
		return new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(attemptReservation(index%4, index/4, brokerID)){
					System.out.println("Broker "+brokerID+" refund seat "+index%4 +" - "+ index/4);
					buttonArray[index].setBackground(Color.white);
					buttonArray[index].setText("seat "+(index+1));
					buttonArray[index].setForeground(Color.black);
				}
			}
		};
	}
	*/
	

}
