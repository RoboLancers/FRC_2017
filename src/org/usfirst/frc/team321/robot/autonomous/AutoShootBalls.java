package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveForward;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightEncoder;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightTime;
import org.usfirst.frc.team321.robot.autonomous.subroutines.RevUpMotor;
import org.usfirst.frc.team321.robot.autonomous.subroutines.ShootBall;
import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnInPlace;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.subsystems.IntakeFlap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShootBalls extends CommandGroup {
	public AutoShootBalls(boolean isLeft) {
		addSequential(new MoveStraightTime(0, 0.5));
		addSequential(new MoveStraightEncoder(0.8, 2.84));
		addSequential(new TurnInPlace(isLeft ? -90 : 90, 1.3));
		addSequential(new DSolenoidToggle(Robot.intakeflap, IntakeFlap.intakeflap, DoubleSolenoid.Value.kReverse));
		addSequential(new MoveStraightTime(0.8, 2));
		addSequential(new MoveStraightTime(0, 3));
		addSequential(new RevUpMotor(true));
		addSequential(new MoveForward(-1, 0.4));
		addSequential(new TurnInPlace(isLeft ? -90 : 90, 1.3));
		addSequential(new MoveForward(1, 3));
		addSequential(new ShootBall(5));
		addSequential(new RevUpMotor(false));
	}
}
