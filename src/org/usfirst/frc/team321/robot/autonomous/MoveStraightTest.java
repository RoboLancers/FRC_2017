package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightNavX;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveStraightTest extends CommandGroup {
	public MoveStraightTest() {
		addSequential(new MoveStraightNavX(0.7));
	}
}
