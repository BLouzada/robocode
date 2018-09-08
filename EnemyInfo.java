package ubots;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.geom.Point2D;
import java.util.Objects;

public class EnemyInfo {

	private Point2D.Double coordinates;
	private double bearing;
	private double distance;
	private double heading;
	private double velocity;
	private String name;

	public EnemyInfo() {
		reset();
	}

	public void reset() {
		coordinates = new Point2D.Double(0, 0);
		bearing = 0;
		distance = 0;
		heading = 0;
		velocity = 0;
		name = "";
	}

	public void update(ScannedRobotEvent event, AdvancedRobot robot) {
		this.bearing = event.getBearing();
		distance = event.getDistance();
		heading = event.getHeading();
		name = event.getName();
		double absBearingDeg = calculateAbsoluteBearing(event, robot);
		double updatedX = getUpdatedX(event, robot, absBearingDeg);
		double updatedY = getUpdateY(event, robot, absBearingDeg);
		coordinates.setLocation(updatedX, updatedY);

	}

	private double getUpdateY(ScannedRobotEvent event, AdvancedRobot robot, double absBearingDeg) {
		return robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) * event.getDistance();
	}

	private double getUpdatedX(ScannedRobotEvent event, AdvancedRobot robot, double absBearingDeg) {
		return robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) * event.getDistance();
	}

	private double calculateAbsoluteBearing(ScannedRobotEvent event, AdvancedRobot robot) {
		double absBearingDeg = (robot.getHeading() + event.getBearing());
		return absBearingDeg < 0 ? absBearingDeg + 360 : absBearingDeg;
	}

	public boolean equals(Object obj) {
		if (obj instanceof EnemyInfo) {
			return Objects.equals(name, ((EnemyInfo) obj).getName());
		}
		return false;
	}

	public int hashCode() {
		return Objects.hash(name);
	}

	public boolean isNotScanned() {
		return "".equals(name);
	}

	public double getX() {
		return coordinates.getX();
	}

	public double getY() {
		return coordinates.getY();
	}

	public double getBearing() {
		return bearing;
	}

	public double getDistance() {
		return distance;
	}

	public double getHeading() {
		return heading;
	}

	public double getVelocity() {
		return velocity;
	}

	public String getName() {
		return name;
	}

}
