package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveTowardsPeg;
import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnTowardsPeg;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TurnTowardsGearTest extends CommandGroup {
	public TurnTowardsGearTest() {
		addSequential(new TurnTowardsPeg(0.3, 6, true));
		addSequential(new MoveTowardsPeg(0.6, 4));
	}
}
