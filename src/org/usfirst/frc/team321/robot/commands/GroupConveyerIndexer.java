package org.usfirst.frc.team321.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GroupConveyerIndexer extends CommandGroup {
	public GroupConveyerIndexer() {
		addParallel(new UseConveyor());
		addParallel(new UseIndexer());
	}
	
	public GroupConveyerIndexer(double power) {
		addParallel(new UseConveyor(power));
		addParallel(new UseIndexer(power));
	}
}
