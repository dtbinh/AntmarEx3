package Robot.Behaviours;

import java.util.ArrayList;

import lejos.nxt.LCD;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Junction implements Behavior{
	private boolean suppressed;
	private boolean takeControl;
	private SensorPort sp;
	private int blackRight; private int blackLeft;
	private int whiteRight; private int whiteLeft;
	private NXTRegulatedMotor right;
	private NXTRegulatedMotor left;
	private int patternIndex = 0;
	private ArrayList<Integer> pattern;
	
	public Junction(NXTRegulatedMotor right, NXTRegulatedMotor left, int whiteRight, int whiteLeft, int blackRight, int blackLeft, ArrayList<Integer> pattern){
		super();
		this.suppressed = false;
		this.takeControl = false;
		this.whiteRight = whiteRight;
		this.blackRight = blackRight;
		this.whiteLeft = whiteLeft;
		this.blackLeft = blackLeft;
		this.right = right;
		this.left = left;
		this.pattern = pattern;
		
	}
	
	public boolean takeControl() {
		
		return Math.abs(SensorPort.S2.readValue() - this.blackLeft) <= 3 &&  Math.abs(SensorPort.S4.readValue() - this.blackRight) <= 3;
		
	}

	public void action() {
		right.stop();
		left.stop();
		pattern();
		right.stop();
		left.stop();
	}

	public void suppress() {
		suppressed = true;

	}
	
	public void pattern(){
		LCD.clear();
		LCD.drawString(pattern.get(patternIndex) + "", 0, 0);
		Delay.msDelay(200);
		if(pattern.get(patternIndex) == 0){
			left.rotate(50, true);
			right.rotate(440);
			
		}
		else if(pattern.get(patternIndex) == 2){
			right.rotate(50, true);
			left.rotate(440);
		}
		else{
			left.rotate(100,true);
			right.rotate(100);
		}
			
		patternIndex = (patternIndex + 1) % pattern.size();
	}
}

