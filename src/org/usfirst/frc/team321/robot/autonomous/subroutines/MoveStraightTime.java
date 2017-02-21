package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveStraightTime extends Command {
	
	private Timer timer = new Timer();
	public double degrees;
	public double power;
	public double seconds;
	
	public MoveStraightTime(double power, double degrees, double seconds) {
		requires(Robot.drivetrain);
		requires(Robot.sensors);
		this.power = power;
		this.degrees = degrees;
		this.seconds = seconds;
    }

    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	Robot.drivetrain.setLeftPowers(Robot.sensors.moveInHeading(power, degrees)[0]);
    	Robot.drivetrain.setRightPowers(Robot.sensors.moveInHeading(power, degrees)[1]);
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    }

    protected void interrupted() {
    	end();
    }

	protected boolean isFinished() {
		return timer.get() > seconds;
	}

}