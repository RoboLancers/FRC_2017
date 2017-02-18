package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EjectGearWithSensor extends Command {

    public EjectGearWithSensor() {
        requires(Robot.gearholder);
    }

    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.sensors.isGearPenetrated()) {
    		Robot.gearholder.openDoor();
    	} else {  
    		Robot.gearholder.closeDoor();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
