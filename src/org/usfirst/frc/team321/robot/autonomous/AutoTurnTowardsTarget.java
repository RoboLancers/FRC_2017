package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnTowardsTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTurnTowardsTarget extends CommandGroup {
	public AutoTurnTowardsTarget() {
		addSequential(new TurnTowardsTarget(0));
	}
}
