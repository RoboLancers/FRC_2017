package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnTowardsPeg extends Command {

	private double power;
	private double seconds = 6;
	private double currentAngle;
	private double[] motorSpeed;
	private boolean isLeft;
	Timer timer = new Timer();
	
	public TurnTowardsPeg(double power, double timeout, boolean isLeft) {
		requires(Robot.drivetrain);
		Robot.gearholder.closeDoor();
		this.power = power;
		seconds = timeout;
		this.isLeft = isLeft;
    }
	
	public TurnTowardsPeg(double power) {
		requires(Robot.drivetrain);
		Robot.gearholder.closeDoor();
		this.power = power;
		seconds = 6;
		isLeft = true;
	}

    protected void initialize() {
    	currentAngle = 1000;
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	if (Robot.camera.gearTargetDetected()) {
    		currentAngle = Robot.camera.gearTargetAngle();
    		motorSpeed = RobotUtil.moveToTarget(power, currentAngle, 0);
    		Robot.drivetrain.setLeftPowers(motorSpeed[1]);
    		Robot.drivetrain.setRightPowers(motorSpeed[0]);
    	} else {
    		currentAngle = 1000;
        	Robot.drivetrain.setLeftPowers(power * (isLeft ? -1 : 1));
        	Robot.drivetrain.setRightPowers(power * (isLeft ? 1 : -1));
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
		return Math.abs(currentAngle) <= 8 || timer.get() > seconds;
	}
}
