package FeedbackAndFriends;

import Robot.Behaviours.DriveForwards;
import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Part2 extends Robot.RobotDemo implements Runnable{

	private final NXTRegulatedMotor wheelLeft = Motor.C;
	private final NXTRegulatedMotor wheelRight = Motor.B;
	private final LightSensor lightLeft;
	private final LightSensor lightRight;
	private int MidPoint = 80;
	private int Turn = 90;
	private int MoveSpeed = 50;
	private int[] pattern;
	
	public Part2(){
		super();
		lightLeft = new LightSensor(SensorPort.S2);
		lightRight = new LightSensor(SensorPort.S4);
		
	}
	
	public static void main(String args[]){
		Part2 demo = new Part2();
		Button.waitForAnyPress();
		demo.run(); ArrayList<int>;

	}

	@Override
	public void run() {

		
		System.out.println("Press button to go!");
		pilot.setTravelSpeed(MoveSpeed);
		wheelLeft.setSpeed(MoveSpeed);
		wheelRight.setSpeed(MoveSpeed);
		Button.waitForAnyPress();
		
		Behavior[] behaviours = {new DriveForwards(pilot)};
		Arbitrator arby = new Arbitrator(behaviours);
		arby.start();
		
		
	}
	
	public void createPattern() {
		//creates an array list of ints that stores the pattern which would be created by the buttons

	}
}

// Create a pattern (must be looping?) (using buttons on robot?)
// pass pattern to findJunction behaviour as well as current position in the pattern
// when this behaviour detects a junction, it looks into the pattern and decides what to do
// actions for the pattern would be stored in this behaviours action method (or would we need to seperate them out into other methods within the behaviour that the action would call)