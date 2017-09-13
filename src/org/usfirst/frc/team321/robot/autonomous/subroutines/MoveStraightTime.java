package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightTime extends Command {
	
	private Timer timer = new Timer();
	private boolean hasRobotAngle;
	public double degrees;
	public double power;
	public double seconds;
	
	public MoveStraightTime(double power, double degrees, double seconds) {
		requires(Robot.drivetrain);
		this.power = power;
		this.degrees = degrees;
		this.seconds = seconds;
		hasRobotAngle = true;
    }
	
	public MoveStraightTime(double power, double seconds) {
		requires(Robot.drivetrain);
		this.power = power;
		this.seconds = seconds;
		hasRobotAngle = false;
	}

    protected void initialize() {
    	if (hasRobotAngle) {
    		this.degrees = Robot.sensors.getRobotAngle();
    	}
    	
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	Robot.drivetrain.setAllPowers(power);
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    }

    protected void interrupted() {
    	Robot.drivetrain.setAllPowers(0);
    }

	protected boolean isFinished() {
		return timer.get() >= seconds;
	}

}