package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveTowardsGear extends Command {

	private double power;
	
	public MoveTowardsGear(double power) {
		requires(Robot.drivetrain);
		requires(Robot.camera);
		this.power = power;
    }

    protected void initialize() {

    }

    protected void execute() {
    	if (Robot.camera.gearTargetDetected()) {
    		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(power, Robot.camera.gearTargetAngle(), 0)[0] / 3);
    		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(power, Robot.camera.gearTargetAngle(), 0)[1] / 3);
    	} else {
    		Robot.drivetrain.setAllPowers(0);
    		System.out.println("No Target Detected");
    	}
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    }

    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return !Robot.camera.gearTargetDetected() || Robot.sensors.isGearPenetrated();
	}
}