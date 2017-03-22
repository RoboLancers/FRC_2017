package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightNavX extends Command{
	
	private double targetAngle;
	private double power;
	private boolean hasTargetAngle;
	
	public MoveStraightNavX(double power){
		this.power = power;
		hasTargetAngle = false;
	}
	
	public MoveStraightNavX(double power, double targetAngle)  {
		this.power = power;
		this.targetAngle = targetAngle;
		hasTargetAngle = true;
	}

	@Override
	protected void initialize() {
		if (!hasTargetAngle) {
			this.targetAngle = Robot.sensors.getRobotAngle();
		}
		Robot.sensors.navX.resetDisplacement();
	}

	@Override
	protected void execute() {
		double[] motorSpeed = RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), targetAngle);
    	Robot.drivetrain.setLeftPowers(motorSpeed[0]);
		Robot.drivetrain.setRightPowers(motorSpeed[1]);
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
		return false;
	}
	
}