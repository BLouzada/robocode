package ubots;

import java.awt.geom.Point2D;

public class EnemyPredictor {

	
	private EnemyInfo enemy;

	public EnemyPredictor(EnemyInfo enemy) {
		super();
		this.enemy = enemy;
	}
	
	public Point2D.Double getPredictedPoint(long time) {
		double axisX = enemy.getX() + Math.sin(Math.toRadians(enemy.getHeading())) * enemy.getVelocity() * time;
		double axisY = enemy.getY() + Math.cos(Math.toRadians(enemy.getHeading())) * enemy.getVelocity() * time;
		return new Point2D.Double(axisX, axisY);
	}
	
}
