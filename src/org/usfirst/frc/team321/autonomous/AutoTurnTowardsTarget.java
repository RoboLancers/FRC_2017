package org.usfirst.frc.team321.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTurnTowardsTarget extends CommandGroup {
	public AutoTurnTowardsTarget() {
		addSequential(new MoveTowardsTarget(0));
	}
}
