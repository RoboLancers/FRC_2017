package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightNavX extends Command{
	
	private double currentAngle;
	private double power, distance;
	
	public MoveStraightNavX(double power, double distance){
		this.power = power;
		this.distance = distance;
	}

	@Override
	protected void initialize() {
		currentAngle = Robot.sensors.getRobotAngle();
		Robot.sensors.navX.resetDisplacement();
	}

	@Override
	protected void execute() {
		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), currentAngle)[0]);
		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), currentAngle)[1]);
	}

	@Override
	protected void end() {
		Robot.drivetrain.setAllPowers(0);
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.setAllPowers(0);
	}

	@Override
	protected boolean isFinished() {
		return Robot.sensors.getRobotDisplacement() > distance;
	}
	
}
