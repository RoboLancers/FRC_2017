package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveTowardsTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTurnTowardsTarget extends CommandGroup {
	public AutoTurnTowardsTarget() {
		addSequential(new MoveTowardsTarget(0));
	}
}
