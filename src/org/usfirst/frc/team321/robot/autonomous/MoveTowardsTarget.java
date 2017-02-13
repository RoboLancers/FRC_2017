package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveTowardsTarget extends Command {

	private double power;
	private double ratio;
	private boolean hasFinished;
	
	public MoveTowardsTarget(double power) {
		requires(Robot.drivetrain);
		requires(Robot.camera);
		this.power = power;
		if (power == 0) {
			ratio = 3; 
		} else {
			ratio = 1;
		}
    }

    protected void initialize() {
    	hasFinished = false;
    }

    protected void execute() {
    	if (Robot.camera.gearTargetDetected()) {
    		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(power, Robot.camera.gearTargetAngle(), 0)[0] / ratio);
    		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(power, Robot.camera.gearTargetAngle(), 0)[1] / ratio);
    	} else {
    		Robot.drivetrain.setAllPowers(0);
    		System.out.println("No Target Detected");
    	}
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    	hasFinished = true;
    }

    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return hasFinished;
	}
}
