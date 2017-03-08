package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveTowardsPeg;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightEncoder;
import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnInPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGearAndCrossLine extends CommandGroup {
	public double leftVal = 1;
	
	public AutoGearAndCrossLine(boolean isLeft) {
		if (!isLeft) leftVal = -1;
		else leftVal = 1;
		
		addSequential(new MoveStraightEncoder(0.7, 2.65, 0));
		addSequential(new TurnInPlace(leftVal * -45, 2));
		addSequential(new MoveTowardsPeg(0.7));
		addSequential(new MoveStraightEncoder(-0.5, 0.85, leftVal * -45));
		addSequential(new TurnInPlace(0, 2));
		addSequential(new MoveStraightEncoder(0.7, 2));
	}
}