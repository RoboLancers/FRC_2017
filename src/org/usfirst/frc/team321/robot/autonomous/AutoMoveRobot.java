package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveForwardTime;
import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnInPlace;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoMoveRobot extends CommandGroup {
	public AutoMoveRobot() {
		addSequential(new MoveForwardTime(1, 2));
		addSequential(new MoveForwardTime(-1, 2));
		addSequential(new TurnInPlace(90, 3));
		addSequential(new TurnInPlace(0, 3));
	}
}
