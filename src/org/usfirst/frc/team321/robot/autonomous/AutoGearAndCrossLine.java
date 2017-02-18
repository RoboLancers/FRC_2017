package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightEncoder;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveTowardsGear;
import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnInPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGearAndCrossLine extends CommandGroup {
	public AutoGearAndCrossLine() {
		addSequential(new MoveTowardsGear(0.7));
		addSequential(new MoveStraightEncoder(-0.5, 0.7));
		addSequential(new TurnInPlace(90, 2));
		addSequential(new MoveStraightEncoder(2, 1));
		addSequential(new TurnInPlace(0, 2));
		addSequential(new MoveStraightEncoder(2.6, 1));
	}
}