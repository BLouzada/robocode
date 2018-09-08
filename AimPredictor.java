package ubots;

import robocode.AdvancedRobot;

import java.awt.geom.Point2D;

public class AimPredictor {

	private AdvancedRobot robot;
	private EnemyInfo enemy;
	
	public AimPredictor(AdvancedRobot robot, EnemyInfo enemy) {
		super();
		this.robot = robot;
		this.enemy = enemy;
	}
	
	public void point(BulletPredictor bullet) {
		EnemyPredictor enemyPredictor = new EnemyPredictor(enemy);
		// calcula a quantidade de graus para mover a mira
		double enemyAngle = RobotCalculator.calculateAbsoluteBearing(new Point2D.Double(robot.getX(), robot.getY()),
				enemyPredictor.getPredictedPoint(bullet.getTime()));
		robot.setTurnGunRight(RobotCalculator.calculateNormalizedBearing(enemyAngle - robot.getGunHeading()));
	}
	
	
	
}
