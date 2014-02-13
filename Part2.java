
import java.util.ArrayList;

import Robot.Behaviours.DriveForwards;
import Robot.Behaviours.FollowLineLeft;
import Robot.Behaviours.FollowLineRight;
import Robot.Behaviours.Junction;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class Part2 extends Robot.RobotDemoNoExit implements Runnable {

	private final NXTRegulatedMotor wheelLeft = Motor.C;
	private final NXTRegulatedMotor wheelRight = Motor.B;
	private final LightSensor lightLeft;
	private final LightSensor lightRight;
	private int MoveSpeed = 130;
	private ArrayList<Integer> pattern;
	private int whiteRight;
	private int blackRight;
	private int whiteLeft;
	private int blackLeft;
	private int dir = 0;

	public Part2() {
		super();
		lightLeft = new LightSensor(SensorPort.S2);
		lightRight = new LightSensor(SensorPort.S4);
		pattern = new ArrayList<Integer>();

		Button.ESCAPE.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				dir = -1;
			}

			@Override
			public void buttonReleased(Button b) {

			}

		});
		Button.LEFT.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				dir = 0;
			}

			@Override
			public void buttonReleased(Button b) {

			}

		});
		Button.ENTER.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				dir = 1;
			}

			@Override
			public void buttonReleased(Button b) {

			}

		});
		Button.RIGHT.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				dir = 2;
			}

			@Override
			public void buttonReleased(Button b) {

			}

		});

	}

	public static void main(String args[]) {
		Part2 demo = new Part2();
		Button.waitForAnyPress();
		demo.run();

	}

	@Override
	public void run() {

		lightCalib();
		createPattern();
		System.out.println("Press button to go!");
		Button.waitForAnyPress();
		pilot.setTravelSpeed(MoveSpeed);
		wheelLeft.setSpeed(MoveSpeed);
		wheelRight.setSpeed(MoveSpeed);

		Behavior[] behaviours = {
				new DriveForwards(pilot),
				new FollowLineLeft(wheelRight, SensorPort.S2, whiteLeft,
						blackLeft),
				new FollowLineRight(wheelLeft, SensorPort.S4, whiteRight,
						blackRight),
				new Junction(Motor.B, Motor.C, whiteRight, whiteLeft,
						blackRight, blackLeft, pattern) };

		Arbitrator arby = new Arbitrator(behaviours);
		arby.start();

	}

	public void createPattern() {
		LCD.clear();
		LCD.drawString("Enter a Pattern", 0, 0);
		Button.waitForAnyPress();
		Delay.msDelay(100);
		while (dir != -1) {
			pattern.add(dir);
			System.out.println(dir);
			Button.waitForAnyPress();
			Delay.msDelay(200);
		}
		LCD.clearDisplay();
		for (int i = 0; i < pattern.size(); i++) {
			System.out.println(pattern.get(i));
		}

	}

	public void lightCalib() {
		System.out.println("Press button on black");
		Button.waitForAnyPress();
		blackRight = lightRight.getLightValue();
		blackLeft = lightLeft.getLightValue();
		System.out.println("Press button on white");
		Button.waitForAnyPress();
		whiteRight = lightRight.getLightValue();
		whiteLeft = lightLeft.getLightValue();
	}
}


