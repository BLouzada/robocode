package ubots;

import robocode.AdvancedRobot;

public class Cannon {

	private static final int DEGRES_REMAINING_TO_TURN = 10;
	private AdvancedRobot robot;
	private EnemyInfo enemy;

	public Cannon(AdvancedRobot robot, EnemyInfo enemy) {
		this.robot = robot;
		this.enemy = enemy;
	}

	public void init() {
		robot.setAdjustGunForRobotTurn(true);
	}

	public void project() {
		if (enemy.isNotScanned())
			return;
		
		BulletPredictor bullet = new BulletPredictor(enemy.getDistance());
		
		AimPredictor aim = new AimPredictor(robot, enemy);
		aim.point(bullet);
		
		fire(bullet.getPower());
	}

	private void fire(double firePower) {
		//evita deperdicio de disparos
		if (isCool() && isCannonReady()) {
			robot.setFire(firePower);
		}
	}

	private boolean isCannonReady() {		
		return Math.abs(robot.getGunTurnRemaining()) < DEGRES_REMAINING_TO_TURN;
	}

	private boolean isCool() {
		return robot.getGunHeat() == 0;
	}


}
