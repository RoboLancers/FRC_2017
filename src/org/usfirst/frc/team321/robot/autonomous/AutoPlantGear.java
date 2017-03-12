package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightTime;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveTowardsPeg;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.subsystems.GearHolder;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlantGear extends CommandGroup {
	public AutoPlantGear() {
		addSequential(new MoveStraightTime(0, 1));
		addSequential(new MoveTowardsPeg(0.55));
		addSequential(new MoveStraightTime(0, 1));
		addSequential(new MoveStraightTime(-0.4, 1));
		addSequential(new DSolenoidToggle(Robot.gearholder, GearHolder.gearEjector, DoubleSolenoid.Value.kForward));
	}
}
