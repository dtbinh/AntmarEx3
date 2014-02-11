package FeedbackAndFriends;

import Robot.Behaviours.DriveForwards;
import Robot.Behaviours.FollowLineLeft;
import Robot.Behaviours.FollowLineRight;
import lejos.nxt.*;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Part1b extends Robot.RobotDemo implements Runnable{
	private final int DELAY = 20;
	private final LightSensor lightLeft;
	private final LightSensor lightRight;
	private final NXTRegulatedMotor wheelLeft = Motor.C;
	private final NXTRegulatedMotor wheelRight = Motor.B;
	private int whiteRight; private int blackRight;
	private int whiteLeft; private int blackLeft;
	
	public Part1b(){
		super();
		lightLeft = new LightSensor(SensorPort.S2);
		lightRight = new LightSensor(SensorPort.S4);
	}
	
	public void run() {
		
		System.out.println("Press button on black");
		Button.waitForAnyPress();
		blackRight = lightRight.getLightValue();
		blackLeft = lightLeft.getLightValue();
		System.out.println("Press button on white");
		Button.waitForAnyPress();
		whiteRight = lightRight.getLightValue();
		whiteLeft = lightLeft.getLightValue();
		System.out.println("Press button to go!");
		Button.waitForAnyPress();
		
		pilot.setTravelSpeed(100);
		wheelLeft.setSpeed(100);
		wheelRight.setSpeed(100);
		Behavior[] behaviours = {new DriveForwards(pilot),  new FollowLineLeft(wheelRight, SensorPort.S2, whiteLeft, blackLeft), new FollowLineRight(wheelLeft, SensorPort.S4, whiteRight, blackRight)};
		Arbitrator arby = new Arbitrator(behaviours);
		arby.start();
//		boolean turning = false;
//		float speedRight = 0;
//		float speedLeft = 0;
//		
//		wheelLeft.setSpeed(speedLeft);
//		wheelRight.setSpeed(speedRight);
//		if(speedLeft > 0){
//			wheelLeft.forward();
//		}else{
//			wheelLeft.backward();
//		}
//		if(speedRight > 0){
//			wheelRight.forward();
//		}else{Arbitrator arby = new Arbitrator
//			wheelRight.backward();
//		}
//		
//		
//		while(true){
//			LCD.clear();
//			int lightReadingLeft = lightLeft.readValue();
//			int lightReadingRight = lightRight.readValue();
//			LCD.drawString(lightReadingLeft + "", 0, 0);
//			LCD.drawString(lightReadingRight + "", 0, 1);
//			
//			int lightDifference = lightReadingLeft - lightReadingRight;
//			
////			if(lightReadingLeft > 34 && lightReadingRight > 34){
////				speedRight = (50);
////				speedLeft = (50);
////			}
////			else if( lightReadingLeft < 28){
////				speedRight = (Math.abs(lightDifference) * 20);
////				speedLeft = 25;
////				turning = true;
////			}
////			else if(lightReadingRight < 28){
////				speedLeft = (Math.abs(lightDifference) * 20);
////				speedRight = 25;
////				turning = true;Arbitrator arby = new Arbitrator
////			}
////			else{
////				if(lightDifference > 0){
////					speedLeft = (lightDifference/10 * wheelLeft.getMaxSpeed());
////					speedRight = ((14-lightDifference)/10 * wheelRight.getMaxSpeed());
////				}
////				else
////					speedRight = (Math.abs(lightDifference)/10 * wheelRight.getMaxSpeed());
////					speedLeft = ((14-Math.abs(lightDifference))/10 * wheelLeft.getMaxSpeed());
////			}
//			
//			speedRight = (lightReadingRight - 23)/14 * wheelRight.getMaxSpeed()*1/3;
//			speedLeft = (lightReadingLeft - 23)/14 * wheelLeft.getMaxSpeed()*1/3;
//			
//			LCD.drawString("" + speedLeft, 0, 2);
//			LCD.drawString("" + speedRight, 0, 3);
//			
//			
//			if(speedLeft < 75 && !turning){
//				speedLeft = 75;
//				turning = false;
//			}else if(speedLeft > 250){
//				speedLeft = 250;
//			}
//			if(speedRight < 75 && !turning){
//				speedRight = 75;
//				turning = false;
//			}else if(speedRight > 250){
//				speedRight = 250;
//			}
//			
//			wheelLeft.setSpeed(speedLeft);
//			wheelRight.setSpeed(speedRight);
//			if(speedLeft > 0){
//				wheelLeft.forward();
//			}else{
//				wheelLeft.backward();
//			}
//			if(speedRight > 0){
//				wheelRight.forward();
//			}else{
//				wheelRight.backward();
//			}
//			
//			
//			
//			Delay.msDelay(DELAY);
//		}
	}
	
	public static void main(String args[]){
		Part1b demo = new Part1b();
		Button.waitForAnyPress();
		demo.run();

	}
}
