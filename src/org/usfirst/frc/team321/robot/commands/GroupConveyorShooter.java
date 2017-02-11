package org.usfirst.frc.team321.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GroupConveyorShooter extends CommandGroup {
	public GroupConveyorShooter() {
		addParallel(new ConveyBall());
		addParallel(new UseShooter());
	}
}
