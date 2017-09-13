package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveTowardsPeg extends Command {

	private double power;
	private double timeout = 6;
	private double[] motorPower;
	Timer timer = new Timer();
	
	public MoveTowardsPeg(double power, double timeout) {
		requires(Robot.drivetrain);
		Robot.gearholder.closeDoor();
		this.power = power;
		this.timeout = timeout;
    }
	
	public MoveTowardsPeg(double power) {
		requires(Robot.drivetrain);
		Robot.gearholder.closeDoor();
		this.power = power;
		this.timeout = 6;
	}

    protected void initialize() {
    	Robot.sensors.resetNavX();
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	if (Robot.camera.gearTargetDetected()) {
    		motorPower = RobotUtil.moveToTarget(power, Robot.camera.gearTargetAngle() * 1.3, 0);
    		Robot.drivetrain.setLeftPowers(motorPower[1]);
    		Robot.drivetrain.setRightPowers(motorPower[0]);
    	} else {
    		Robot.drivetrain.setAllPowers(power);
    	}
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    	if (timer.get() < timeout) {
            Robot.gearholder.openDoor();
    	}
    }

    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return Robot.sensors.isGearPenetrated() || timer.get() > timeout;
	}
}