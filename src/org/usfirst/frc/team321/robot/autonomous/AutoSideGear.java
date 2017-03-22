package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveForward;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightTime;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveTowardsPeg;
import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnTowardsPeg;
import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnUntilPeg;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.subsystems.GearHolder;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSideGear extends CommandGroup {
	public AutoSideGear(boolean isLeft) {
		addSequential(new MoveForward(0.5, 2.5));
		//addSequential(new MoveStraightEncoder(0.7, 2.64));
		addSequential(new TurnUntilPeg(0.3, 4, isLeft));
		addSequential(new TurnTowardsPeg(0.3, 4, isLeft));
		addSequential(new MoveTowardsPeg(0.6, 3));
		addSequential(new MoveStraightTime(0, 1));
		addSequential(new MoveStraightTime(-0.4, 1));
		addSequential(new DSolenoidToggle(Robot.gearholder, GearHolder.gearEjector, DoubleSolenoid.Value.kForward));
	}
}
