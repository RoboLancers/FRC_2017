package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class TurnTowardsTarget extends Command {

	private double power;
	private boolean hasFinished = false;
	
	public TurnTowardsTarget(double power) {
		requires(Robot.drivetrain);
		requires(Robot.camera);
		this.power = power;
    }

    protected void initialize() {
    	hasFinished = false;
    }

    protected void execute() {
    	if (Robot.camera.gearTargetDetected()) {
    		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(power, Robot.camera.gearTargetAngle(), 0)[0] / 3);
    		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(power, Robot.camera.gearTargetAngle(), 0)[1] / 3);
    	} else {
    		Robot.drivetrain.setAllPowers(0);
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
		try {
			return Robot.camera.gearTargetAngle() == 0 ||
					Robot.camera.boilerTargetAngle() == 0;
		} catch (Exception e) {
			return false;
		}
	}
}