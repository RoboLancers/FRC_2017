package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightNavX;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveTowardsGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlantGear extends CommandGroup {
	public AutoPlantGear() {
		addSequential(new MoveTowardsGear(0.7));
		addSequential(new MoveStraightNavX(-0.5, 1));
	}
}
