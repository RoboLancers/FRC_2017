package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveForwardTest extends CommandGroup {

	public MoveForwardTest() {
		addSequential(new MoveForward(1, 1));
	}
}
