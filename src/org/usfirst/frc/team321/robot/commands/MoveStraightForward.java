package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.PIDConstant;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightForward extends Command {

    double rightPower, leftPower;
    double distanceInMeters;
    double encoderRotation;
    double timeToMove;
    double startTime;
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
    public MoveStraightForward(double distance, double power, double time) {
    	
		requires(Robot.drivetrain);
		this.distanceInMeters = distance;
		this.leftPower = power;
		this.rightPower = power;
		this.timeToMove = time;
    }

    @Override
    protected void initialize() {
		lastError = 0;
		Robot.drivetrain.clearEncoder();
		startTime = Timer.getFPGATimestamp();
		encoderRotation = Math.abs(Robot.drivetrain.distanceToEncDegrees(distanceInMeters));
    }

    @Override
    protected void execute() {

		double error = Robot.drivetrain.getLeftSpeedInRPM() - Robot.drivetrain.getRightSpeedInRPM();
		rightPower += PIDConstant.kP * error;
		rightPower += PIDConstant.kD * lastError;
	
		lastError = error;
	
		Robot.drivetrain.setRightPowers(rightPower);
		Robot.drivetrain.setLeftPowers(leftPower);
		
		if (timeToMove != 0 && Timer.getFPGATimestamp() - startTime > timeToMove) {
			Robot.drivetrain.stopMotors();
		}
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