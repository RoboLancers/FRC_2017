package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightEncoder extends Command {

	double power;
    double distanceInMeters;
    double startAngle;
    boolean leftFinished, rightFinished;

    public MoveStraightEncoder(double power, double distance) {
		requires(Robot.drivetrain);
		this.distanceInMeters = distance;
		this.power = power;
		this.startAngle = Robot.sensors.getRobotAngle();
    }

    public MoveStraightEncoder(double power, double distance, double targetAngle) {
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
    	double[] motorSpeed = RobotUtil.moveToTarget(power, Robot.sensors.getRobotAngle(), startAngle);
    	Robot.drivetrain.setLeftPowers(motorSpeed[0]);
		Robot.drivetrain.setRightPowers(motorSpeed[1]);
    }

    @Override
    protected boolean isFinished() {
    	leftFinished = Math.abs(Robot.drivetrain.getLeftDistance()) > Math.abs(distanceInMeters);
		rightFinished = Math.abs(Robot.drivetrain.getRightDistance()) > Math.abs(distanceInMeters);
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