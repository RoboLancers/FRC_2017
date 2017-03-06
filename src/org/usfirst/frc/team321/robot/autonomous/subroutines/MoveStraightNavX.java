package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightNavX extends Command{
	
	private double targetAngle;
	private double power, distance;
	
	public MoveStraightNavX(double power, double distance){
		this.power = power;
		this.distance = distance;
		this.targetAngle = Robot.sensors.getRobotAngle();
	}
	
	public MoveStraightNavX(double power, double distance, double targetAngle)  {
		this.power = power;
		this.distance = distance;
		this.targetAngle = targetAngle;
	}

	@Override
	protected void initialize() {
		Robot.sensors.navX.resetDisplacement();
	}

	@Override
	protected void execute() {
		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), targetAngle)[0]);
		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), targetAngle)[1]);
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
