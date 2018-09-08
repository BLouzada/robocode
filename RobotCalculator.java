package ubots;

import robocode.Rules;

import java.awt.geom.Point2D;

public class RobotCalculator {

	private static final int FIRE_FACTOR = 500;

	private RobotCalculator() {
	}

	public static double calculateAbsoluteBearing(Point2D.Double source, Point2D.Double target) {
		return calculateAbsoluteBearing(source.getX(), source.getY(), target.getX(), target.getY());
	}

	public static double calculateAbsoluteBearing(double sourceX, double sourceY, double targetX, double targetY) {
		double xo = targetX - sourceX;
		double yo = targetY - sourceY;
		double hyp = Point2D.distance(sourceX, sourceY, targetX, targetY);
		double arcSin = Math.toDegrees(Math.asin(xo / hyp));
		double bearing = 0;

		if (isLowerLeft(xo, yo)) { 
			bearing = arcSin;
		} else if (isLowerRight(xo, yo)) { 
			bearing = 360 + arcSin; 
		} else if (isUpperLeft(xo, yo)) { 
			bearing = 180 - arcSin;
		} else if (isUpperRight(xo, yo)) { 
			bearing = 180 - arcSin; 
		}

		return bearing;
	}

	private static boolean isUpperRight(double xo, double yo) {
		return xo < 0 && yo < 0;
	}

	private static boolean isUpperLeft(double xo, double yo) {
		return xo > 0 && yo < 0;
	}

	private static boolean isLowerRight(double xo, double yo) {
		return xo < 0 && yo > 0;
	}

	private static boolean isLowerLeft(double xo, double yo) {
		return xo > 0 && yo > 0;
	}

	public static double calculateNormalizedBearing(double angle) {
		while (angle > 180)
			angle -= 360;
		while (angle < -180)
			angle += 360;
		return angle;
	}

	/**
	 * Calcula a potencia do tiro baseada na distancia
	 * @param distance
	 * @return
	 */
	public static double calculateFirePower(double distance) {
		return Math.min(FIRE_FACTOR / distance, Rules.MAX_BULLET_POWER);
	}

	/**
	 * Calcula a velocidade do tiro de acordo a potencia
	 * 
	 * @param firePower
	 * @return
	 */
	public static double calculateBulletSpeed(double firePower) {
		return 20 - firePower * 3;
	}

	/**
	 * Calcula o tempo que o tiro alcançara o alvo
	 * tempo = distancia / velocidade tiro
	 * @param distance
	 * @param bulletSpeed
	 * @return
	 */
	public static long calculateTime(double distance, double bulletSpeed) {
		return (long) (distance / bulletSpeed);
	}
}
