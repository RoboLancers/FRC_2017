package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossLine extends CommandGroup {
	public AutoCrossLine() {
		addSequential(new MoveStraightTime(1, 0, 3));
	}
}
