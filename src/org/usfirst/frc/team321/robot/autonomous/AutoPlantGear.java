package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightEncoder;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveTowardsPeg;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.subsystems.GearHolder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlantGear extends CommandGroup {
	public AutoPlantGear() {
		addSequential(new MoveTowardsPeg(0.7));
		addSequential(new MoveStraightEncoder(-0.87, 1));
		addSequential(new DSolenoidToggle(Robot.gearholder, GearHolder.gearEjector));
	}
}
