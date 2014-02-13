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
	private final int speed = 150;
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
		
		pilot.setTravelSpeed(speed);
		wheelLeft.setSpeed(speed);
		wheelRight.setSpeed(speed);
		Behavior[] behaviours = {new DriveForwards(pilot),  new FollowLineLeft(wheelRight, SensorPort.S2, whiteLeft, blackLeft), new FollowLineRight(wheelLeft, SensorPort.S4, whiteRight, blackRight)};
		Arbitrator arby = new Arbitrator(behaviours);
		arby.start();

	}
	
	public static void main(String args[]){
		Part1b demo = new Part1b();
		Button.waitForAnyPress();
		demo.run();

	}
}
