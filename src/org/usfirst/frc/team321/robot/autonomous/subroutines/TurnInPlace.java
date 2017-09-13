package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnInPlace extends Command {
	
	private Timer timer = new Timer();
	private double degrees;
	private double seconds;
	private double currentAngle;
	
	public TurnInPlace(double degrees, double seconds) {
		this.degrees = degrees;
		this.seconds = seconds;
	}

	public void initialize() {
		currentAngle = Robot.sensors.getRobotAngle();
		timer.reset();
		timer.start();
	}
	
	public void execute() {
		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(0, Robot.sensors.getRobotAngle(), degrees + currentAngle)[0]);
		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(0, Robot.sensors.getRobotAngle(), degrees + currentAngle)[1]);
	}
	
	public void end() {
		Robot.drivetrain.setAllPowers(0);
	}
	
	@Override
	protected boolean isFinished() {
		return timer.get() >= seconds;
	}
}
