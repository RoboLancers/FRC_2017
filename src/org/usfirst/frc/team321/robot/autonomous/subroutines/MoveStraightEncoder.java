package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightEncoder extends Command {

	double power;
    double distanceInMeters;
    double startAngle;
    boolean leftFinished, rightFinished;

    public MoveStraightEncoder(double distance, double power) {
		requires(Robot.drivetrain);
		this.distanceInMeters = distance;
		this.power = power;
		this.startAngle = Robot.sensors.getRobotAngle();
    }
    
    public MoveStraightEncoder(double distance, double power, double targetAngle) {
    	requires(Robot.drivetrain);
		this.distanceInMeters = distance;
		this.power = power;
		this.startAngle = targetAngle;
    }

    @Override
    protected void initialize() {
		Robot.drivetrain.resetEncoders();
    }

    @Override
    protected void execute() {
		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), startAngle)[1]);
		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), startAngle)[0]);
    }

    @Override
    protected boolean isFinished() {
    	leftFinished = Math.abs(Robot.drivetrain.getLeftDistance()) > distanceInMeters;
		rightFinished = Math.abs(Robot.drivetrain.getRightDistance()) > distanceInMeters;
		return leftFinished || rightFinished;
    }

    @Override
    protected void end() {
    	Robot.drivetrain.stopMotors();
    }

    @Override
    protected void interrupted() {
    	Robot.drivetrain.stopMotors();
    }

}