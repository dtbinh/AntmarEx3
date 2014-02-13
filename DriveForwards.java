package Robot.Behaviours;

import lejos.nxt.LCD;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class DriveForwards implements Behavior {

	private DifferentialPilot pilot;
	private boolean suppressed;
	
	
	public DriveForwards(DifferentialPilot pilot){
		super();
		this.pilot = pilot;
		this.suppressed = false;
	}
	
	@Override
	public boolean takeControl() {
		
		return true;
	}

	@Override
	public void action() {
		//LCD.clearDisplay();
		//System.out.println("FORWARDS");
		pilot.forward();
		while(!suppressed){
			Thread.yield();
		}
		pilot.stop();
		suppressed = false;
		
	}

	@Override
	public void suppress() {
		this.suppressed = true;
		

	}

}

