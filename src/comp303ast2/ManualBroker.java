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
			
			Thread.sleep(1000);
		}catch( InterruptedException e){
			
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
					System.out.println("Broker "+brokerID+" reserved seat "+index%4 +"-"+ index/4);
					JButton currentButton = buttonArray[index];
					currentButton.setBackground(Color.blue);
					currentButton.setText("R: " + brokerID);
					currentButton.setForeground(Color.white);
					//currentButton.removeActionListener(currentButton.getActionListeners()[0]);
					//currentButton.addActionListener(createRefundButtonListener(index, brokerID));
				}
			}
		};
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
