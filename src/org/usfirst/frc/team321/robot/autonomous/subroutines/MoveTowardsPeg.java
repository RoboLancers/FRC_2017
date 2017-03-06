package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveTowardsPeg extends Command {

	private double power;
	
	public MoveTowardsPeg(double power) {
		requires(Robot.drivetrain);
		requires(Robot.camera);
		this.power = power;
    }

    protected void initialize() {

    }

    protected void execute() {
    	if (Robot.camera.gearTargetDetected()) {
    		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(power, RobotUtil.squareAndKeepSign(Robot.camera.gearTargetAngle()), 0)[0]);
    		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(power, RobotUtil.squareAndKeepSign(Robot.camera.gearTargetAngle()), 0)[1]);
    	} else {
    		Robot.drivetrain.setAllPowers(0.2);
    	}
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    	Robot.gearholder.openDoor();
    }

    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return !Robot.camera.gearTargetDetected() || Robot.sensors.isGearPenetrated();
	}
}