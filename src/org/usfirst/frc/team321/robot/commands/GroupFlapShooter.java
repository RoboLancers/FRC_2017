package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.IntakeFlap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GroupFlapShooter extends CommandGroup {
	public GroupFlapShooter() {
		addParallel(new UseShooter());
		addParallel(new DSolenoidHold(Robot.intakeflap, IntakeFlap.intakeflap, DoubleSolenoid.Value.kForward));
	}
}
