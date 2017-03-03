package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightEncoder extends Command {

    double rightPower, leftPower;
    double distanceInMeters;
    boolean leftFinished, rightFinished;

    public MoveStraightEncoder(double distance, double power) {
		requires(Robot.drivetrain);
		this.distanceInMeters = distance;
		this.leftPower = power;
		this.rightPower = power;
    }

    @Override
    protected void initialize() {
		Robot.drivetrain.resetEncoders();
    }

    @Override
    protected void execute() {
		Robot.drivetrain.setRightPowers(rightPower);
		Robot.drivetrain.setLeftPowers(leftPower);
    }

    @Override
    protected boolean isFinished() {
    	//leftFinished = Math.abs(Robot.drivetrain.getLeftDistance()) > distanceInMeters;
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