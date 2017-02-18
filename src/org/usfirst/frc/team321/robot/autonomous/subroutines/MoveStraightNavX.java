package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightNavX extends Command{
	
	private boolean hasFinished = false;
	double power, distance;
	
	public MoveStraightNavX(double power, double distance){
		this.power = power;
		this.distance = distance;
	}

	@Override
	protected void initialize() {
		hasFinished = false;
		Robot.sensors.resetNavX();
	}

	@Override
	protected void execute() {
		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), 0)[0]);
		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), 0)[1]);
	}

	@Override
	protected void end() {
		hasFinished = true;
		Robot.drivetrain.setAllPowers(0);
	}

	@Override
	protected void interrupted() {
		hasFinished = true;
		Robot.drivetrain.setAllPowers(0);
	}

	@Override
	protected boolean isFinished() {
		hasFinished = Robot.sensors.getRobotDisplacement() < distance;
		return hasFinished;
	}
	
}
