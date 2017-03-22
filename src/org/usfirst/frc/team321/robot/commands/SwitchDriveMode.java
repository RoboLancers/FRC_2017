package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain.DriveMode;

import edu.wpi.first.wpilibj.command.Command; 

public class SwitchDriveMode extends Command {

	private boolean hasFinished = false;
	DriveMode driveMode;
	
	public SwitchDriveMode(DriveMode driveMode) {
		this.driveMode = driveMode;
		hasFinished = false;
	}

	protected void initialize() {
		Drivetrain.driveMode = driveMode;
		hasFinished = false;
	}

	protected void execute() {
		Drivetrain.driveMode = driveMode;
	}

	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end() {
		Drivetrain.driveMode = DriveMode.DRIVING;
		hasFinished = true;
	}

	protected void interrupted() {
		Drivetrain.driveMode = DriveMode.DRIVING;
		hasFinished = true;
	}
}
