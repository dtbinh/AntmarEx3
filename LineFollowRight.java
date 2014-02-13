package Robot.Behaviours;

import lejos.nxt.LCD;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class FollowLineRight implements Behavior{

	private boolean suppressed;
	private boolean takeControl;
	private SensorPort sp;
	private int black;
	private int white;
	private NXTRegulatedMotor left;
	
	
	public FollowLineRight(NXTRegulatedMotor left, SensorPort sp, int white, int black){
		super();
		this.suppressed = false;
		this.takeControl = false;
		this.white = white;
		this.black = black;
		this.left = left;
		
	}
	
	@Override
	public boolean takeControl() {
		LCD.clear();
		LCD.drawString(SensorPort.S4.readValue() + "", 0, 0);
		LCD.drawString(Math.abs(SensorPort.S4.readValue() - this.black) + "", 0, 1);
		takeControl = Math.abs(SensorPort.S4.readValue() - this.black) <= 3;
		LCD.drawString(takeControl + "", 0, 2);
		return takeControl;
	}

	@Override
	public void action() {
		//LCD.clearDisplay();
		//System.out.println("FOLLOW RIGHT");
		left.forward();
		while(!suppressed && Math.abs(SensorPort.S4.readValue() - this.black) <= 3){
			Thread.yield();
		}
		suppressed = false;
		left.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;

	}

}
