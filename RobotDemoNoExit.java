package Robot;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;


public class RobotDemoNoExit {
	
	public final DifferentialPilot pilot;
	
	public RobotDemoNoExit(){
		final RegulatedMotor wheelLeft = Motor.C;
		final RegulatedMotor wheelRight = Motor.B;
		final double wheelDiameter = 56.0;
		final double trackWidth = 120.0;
		pilot = new DifferentialPilot(wheelDiameter,trackWidth, wheelLeft, wheelRight);
	}
	
	
	
}
