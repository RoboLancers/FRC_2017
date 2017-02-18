package org.usfirst.frc.team321.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DSolenoidHold extends Command {

	private DoubleSolenoid ds;
	private boolean hasFinished = false;
	Value value = null;

	public DSolenoidHold(Subsystem sub, DoubleSolenoid ds, Value defaultValue){
		requires(sub);
		this.ds = ds;
		this.value = defaultValue;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		ds.set(value == DoubleSolenoid.Value.kForward ? DoubleSolenoid.Value.kReverse : DoubleSolenoid.Value.kForward);
		
		hasFinished = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasFinished;
	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		ds.set(value);
		
		hasFinished = true;
	}
}
