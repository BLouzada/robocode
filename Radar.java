package ubots;

import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class Radar {

	private static final int TIMES_TO_SPIN = 100;
	private static final int ANGLE = 30;
	private byte direction = 1;
	private AdvancedRobot robot;
	private EnemyInfo enemy;

	public void init() {
		robot.setAdjustRadarForGunTurn(true);
	}
	
	public Radar(AdvancedRobot robot, EnemyInfo enemy) {
		this.robot = robot;
		this.enemy = enemy;
	}

	public void scan() {
		if (enemy.isNotScanned()) {
			robot.setTurnRadarRight(360 * TIMES_TO_SPIN);
		} else {			
			robot.setTurnRadarRight(getAngleToTurn());
			changeDirection();
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		if (shouldTrack(e)) {
			enemy.update(e, robot);
		}
	}

	public void onRobotDeath(RobotDeathEvent e) {
		if (wasTracking(e)) {
			enemy.reset();
		}
	}
	
	public boolean shouldTrack(ScannedRobotEvent e) {
		return (enemy.isNotScanned() || isNear(e) || isCurrentTarget(e));
	}

	private boolean isCurrentTarget(ScannedRobotEvent e) {
		return e.getName().equals(enemy.getName());
	}

	private boolean isNear(ScannedRobotEvent e) {
		return e.getDistance() < enemy.getDistance() - 70;
	}
	
	public boolean wasTracking(RobotDeathEvent e) {
		return (e.getName().equals(enemy.getName()));
	}
	
	private void changeDirection() {
		direction *= -1;
	}

	private double getAngleToTurn() {
		double turn = robot.getHeading() - robot.getRadarHeading() + enemy.getBearing();
		turn += ANGLE * direction;
		return turn;
	}

}
