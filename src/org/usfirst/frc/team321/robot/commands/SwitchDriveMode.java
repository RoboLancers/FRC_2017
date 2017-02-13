package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem; 

public class SwitchDriveMode extends Command {

	private boolean hasFinished;
	Drivetrain.DriveMode driveMode;
	
	public SwitchDriveMode(Drivetrain.DriveMode driveMode) {
		this.driveMode = driveMode;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.driveMode = driveMode;
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
		Drivetrain.driveMode = Drivetrain.DriveMode.DRIVING;
		hasFinished = true;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
