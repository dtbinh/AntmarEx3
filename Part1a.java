package FeedbackAndFriends;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.robotics.RangeFinder;
import lejos.util.Delay;

public class Part1a extends Robot.RobotDemo implements Runnable {

	private final RangeFinder ranger;
	private final static int D_DISTANCE = 20;
	private final static int DELAY = 25;

	public Part1a(RangeFinder _ranger) {
		super();
		ranger = _ranger;
	}

	public void run() {

		float speed = 0;
		float k = 0.1f;
		pilot.setTravelSpeed((int) speed);
		pilot.forward();

		while (true) {
			if(speed > 0)
			{
				pilot.setTravelSpeed((int) speed);
				pilot.forward();
			}
			if(speed < 0)
			{
				pilot.setTravelSpeed((int) speed);
				pilot.backward();
			}
			float reading = ranger.getRange();
			float error = reading - D_DISTANCE;

			if (Math.abs(error) < 10) {
				speed = 10 * (error);
			} 
			else {

				if (error > 0) {
					speed = Math.min(200, speed + error * k);
				} 
				else {
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
