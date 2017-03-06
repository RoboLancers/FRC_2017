package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightEncoder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossLine extends CommandGroup {
	public AutoCrossLine() {
		addSequential(new MoveStraightEncoder(1, 5));
	}
}
