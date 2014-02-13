package FeedbackAndFriends;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.RangeFinder;
import lejos.util.Delay;

public class Part1a extends Robot.RobotDemo implements Runnable {

	private final RangeFinder ranger;
	private final int D_DISTANCE;
	private final static int DELAY = 25;
	private boolean distanceChosen = false;
	private int tempDist = 20;

	public Part1a(RangeFinder _ranger) {
		super();
		ranger = _ranger;
		Button.ENTER.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				distanceChosen = true;
			}

			@Override
			public void buttonReleased(Button b) {
				// TODO Auto-generated method stub

			}

		});
		Button.LEFT.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				if (tempDist > 15) {
					tempDist -= 5;
				}

			}
System.out.println("hello");
			@Override
			public void buttonReleased(Button b) {
				// TODO Auto-generated method stub

			}

		});
		Button.RIGHT.addButtonListener(new ButtonListener() {

			@Override
			public void buttonPressed(Button b) {
				if (tempDist <= 35) {
					tempDist += 5;
				}
			}

			@Override
			public void buttonReleased(Button b) {
				// TODO Auto-generated method stub

			}

		});
		while (!distanceChosen) {
			LCD.drawInt(tempDist, 0, 0, 0);
			Delay.msDelay(100);
			LCD.clear();
		}
		D_DISTANCE = tempDist;
	}

	public void run() {

		float speed = 0;
		float k = 0.1f;
		pilot.setTravelSpeed((int) speed);
		pilot.forward();

		while (true) {
			if (speed > 0) {
				pilot.setTravelSpeed((int) speed);
				pilot.forward();
			}
			if (speed < 0) {
				pilot.setTravelSpeed((int) speed);
				pilot.backward();
			}
			float reading = ranger.getRange();
			float error = reading - D_DISTANCE;

			if (Math.abs(error) < 10) {
				speed = 10 * (error);
			} else {

				if (error > 0) {
					speed = Math.min(200, speed + error * k);
				} else {
					speed = Math.max(-200, speed + error * k);
				}
			}
			LCD.clear();

			LCD.drawString(speed + "", 0, 0);
			LCD.drawString(reading + "", 0, 6);

			Delay.msDelay(DELAY);

			Thread.yield();
		}

	}

	public static void main(String[] args) {

		RangeFinder ranger = new OpticalDistanceSensor(SensorPort.S1);
		Part1a demo = new Part1a(ranger);
		Button.waitForAnyPress();
		demo.run();

	}

}
