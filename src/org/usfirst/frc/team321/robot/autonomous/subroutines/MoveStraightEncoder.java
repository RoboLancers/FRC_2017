package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightEncoder extends Command {

    double rightPower, leftPower;
    double distanceInMeters;
    double encoderRotation;
    boolean leftFinished, rightFinished;

    double lastError;

    /**
     * Moves forward a specified distance
     * 
     * @param distance
     *            Distance to go in meters
     * @param power
     *            How fast should the robot move
     * @param time
     *            How long to move in seconds
     */
    public MoveStraightEncoder(double distance, double power) {
		requires(Robot.drivetrain);
		this.distanceInMeters = distance;
		this.leftPower = power;
		this.rightPower = power;
    }

    @Override
    protected void initialize() {
		lastError = 0;
		Robot.drivetrain.clearEncoder();
		encoderRotation = Math.abs(Robot.drivetrain.distanceToEncDegrees(distanceInMeters));
    }

    @Override
    protected void execute() {

		double error = Robot.drivetrain.getLeftSpeedInRPM() - Robot.drivetrain.getRightSpeedInRPM();
		rightPower += Constants.kP * error;
		rightPower += Constants.kD * lastError;
	
		lastError = error;
	
		Robot.drivetrain.setRightPowers(rightPower);
		Robot.drivetrain.setLeftPowers(leftPower);
    }

    @Override
    protected boolean isFinished() {
    	leftFinished = Math.abs(Robot.drivetrain.getLeftEncoderDistance()) > encoderRotation;
		rightFinished = Math.abs(Robot.drivetrain.getRightEncoderDistance()) > encoderRotation;
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