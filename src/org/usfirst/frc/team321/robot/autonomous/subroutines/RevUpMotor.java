package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RevUpMotor extends Command {
	private boolean isMotorRunning;
	
	public RevUpMotor(boolean isRunning) {
		isMotorRunning = isRunning;
    }

    protected void initialize() {
    	if (isMotorRunning) {
    		Robot.shooter.setShooter(0.87);
    	} else {
    		Robot.shooter.setShooter(0);
    	}
    }

    protected void execute() {

    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return true;
	}
}
