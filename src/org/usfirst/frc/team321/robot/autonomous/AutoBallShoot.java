package org.usfirst.frc.team321.robot.autonomous;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightEncoder;
import org.usfirst.frc.team321.robot.autonomous.subroutines.MoveStraightTime;
import org.usfirst.frc.team321.robot.autonomous.subroutines.ShootBall;
import org.usfirst.frc.team321.robot.autonomous.subroutines.TurnInPlace;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.subsystems.IntakeFlap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoBallShoot extends CommandGroup {
	double leftVal = 1;
	
	public AutoBallShoot(boolean isLeft) {
		if (isLeft) leftVal = 1;
		else leftVal = -1;
		
		addSequential(new DSolenoidToggle(Robot.intakeflap, IntakeFlap.intakeflap, DoubleSolenoid.Value.kReverse));
		addSequential(new MoveStraightEncoder(0.7, 2.65));
		addSequential(new TurnInPlace(leftVal * -90, 2));
		addSequential(new MoveStraightTime(0.6, 1));
		addSequential(new MoveStraightTime(0, 1));
		addSequential(new MoveStraightEncoder(-0.7, -0.5));
		addSequential(new TurnInPlace(0, 1));
		addSequential(new MoveStraightTime(-0.7, 2));
		addSequential(new ShootBall(5));
	}
}
