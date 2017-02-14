package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightEncoder;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMoveWithEncoder extends CommandGroup {
	public AutoMoveWithEncoder(){
		addSequential(new MoveStraightEncoder(10, 0.5));
	}
}
