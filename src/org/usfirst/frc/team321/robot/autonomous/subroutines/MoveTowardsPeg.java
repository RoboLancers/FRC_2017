package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveTowardsPeg extends Command {

	private double power;
	private double[] motorPower;
	
	public MoveTowardsPeg(double power) {
		requires(Robot.drivetrain);
		requires(Robot.camera);
		this.power = power;
    }

    protected void initialize() {

    }

    protected void execute() {
    	if (Robot.camera.gearTargetDetected()) {
    		motorPower = RobotUtil.moveToTarget(power, Robot.camera.gearTargetAngle(), 0);
    		Robot.drivetrain.setLeftPowers(motorPower[0]);
    		Robot.drivetrain.setRightPowers(motorPower[1]);
    	} else {
    		Robot.drivetrain.setAllPowers(0.1);
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
		return Robot.sensors.isGearPenetrated() || Math.abs(Robot.sensors.getRobotAngle()) > 25;
	}
}