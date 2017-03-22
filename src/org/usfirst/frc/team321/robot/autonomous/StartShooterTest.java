package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightTime;
import org.usfirst.frc.team321.robot.autonomous.subroutines.RevUpMotor;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartShooterTest extends CommandGroup {
	public StartShooterTest() {
		addSequential(new RevUpMotor(true));
		addSequential(new MoveStraightTime(0, 3));
		addSequential(new RevUpMotor(false));
	}
}
