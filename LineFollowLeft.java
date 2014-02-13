package Robot.Behaviours;

import lejos.nxt.LCD;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class FollowLineLeft implements Behavior{

	private boolean suppressed;
	private boolean takeControl;
	private SensorPort sp;
	private int black;
	private int white;
	private NXTRegulatedMotor right;
	
	
	public FollowLineLeft(NXTRegulatedMotor right, SensorPort sp, int white, int black){
		super();
		this.suppressed = false;
		this.takeControl = false;
		this.white = white;
		this.black = black;
		this.right = right;
		
	}
	
	@Override
	public boolean takeControl() {
		//LCD.clear();
		//LCD.drawString(SensorPort.S2.readValue() + "", 0, 0);
		return Math.abs(SensorPort.S2.readValue() - this.black) <= 3;
	}

	@Override
	public void action() {
		//LCD.clearDisplay();
		//System.out.println("FOLLOW LEFT");
		right.forward();
		while(!suppressed){
			Thread.yield();
		}
		suppressed = false;
		right.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
