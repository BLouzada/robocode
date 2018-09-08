package ubots;

import java.awt.Color;
import java.util.Random;

import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class MambaNegra extends AdvancedRobot {

	private EnemyInfo enemy = new EnemyInfo();
	private Radar radar = new Radar(this, enemy);
	private Cannon gun = new Cannon(this, enemy);
	private BasicMovement movement = new BasicMovement(this);

	public void run() {

		setColors(getRandomcolor(), getRandomcolor(), getRandomcolor());

		enemy.reset();
		radar.init();
		gun.init();

		setTurnRadarRight(360);

		while (true) {
			radar.scan();
			movement.move();
			gun.project();
			execute();

		}
	}


	public void onScannedRobot(ScannedRobotEvent e) {
		radar.onScannedRobot(e);
	}

	public void onRobotDeath(RobotDeathEvent e) {
		radar.onRobotDeath(e);
	}

	private Color getRandomcolor() {
		return new Color(getRandomRgbColor(), getRandomRgbColor(), getRandomRgbColor());
	}

	private int getRandomRgbColor() {
		return new Random().ints(0, 256).findAny().getAsInt();
	}

	private static class BasicMovement {

		private AdvancedRobot robot;

		public BasicMovement(AdvancedRobot robot) {
			this.robot = robot;
		}

		public void move() {
			robot.setTurnRight(-5);
			robot.setAhead(-20);
		}

	}

}
