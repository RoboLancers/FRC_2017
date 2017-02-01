package org.usfirst.frc.team321.autonomous;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForwardTime extends Command {
	
	public double degrees;
	public double power;
	public double seconds;
	public double startTime;
	public boolean hasFinished = false;
	
	/**
	 * Instructions to move robot straight forward with the navX sensor
	 * Set power to 0 to turn in place.
	 */
	public MoveForwardTime(double power, double degrees, double seconds) {
		requires(Robot.drivetrain);
		requires(Robot.sensors);
		this.power = power;
		this.degrees = degrees;
		this.seconds = seconds;
    }
	
	public boolean timeEnded(){
		if ((System.nanoTime() - startTime)/1000000000 < seconds) {
			return false;
		} else {
			return true;
		}
	}

    protected void initialize() {
    	startTime = System.nanoTime();
    	hasFinished = false;
    }

    protected void execute() {
    	if(!timeEnded()){
    		Robot.drivetrain.setLeftPowers(Robot.sensors.moveInHeading(power, degrees)[0]);
    		Robot.drivetrain.setRightPowers(Robot.sensors.moveInHeading(power, degrees)[1]);
    	}else{
    		hasFinished = true;
    	}
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    	hasFinished = true;
    }

    protected void interrupted() {
    	end();
    }

	protected boolean isFinished() {
		return timeEnded();
	}

}