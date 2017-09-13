package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveForwardTime extends Command {

	private Timer timer = new Timer();
	public double power;
	public double seconds;
	public boolean hasFinished = false;
	
	public MoveForwardTime(double power, double seconds) {
		requires(Robot.drivetrain);
		this.power = power;
		this.seconds = seconds;
    }

    protected void initialize() {
    	hasFinished = false;
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	Robot.drivetrain.setLeftPowers(power);
    	Robot.drivetrain.setRightPowers(power);
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    	hasFinished = true;
    }

    protected void interrupted() {
    	end();
    }

	protected boolean isFinished() {
		return timer.get() > seconds;
	}

}
