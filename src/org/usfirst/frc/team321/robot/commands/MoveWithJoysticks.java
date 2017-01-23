package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.utilities.JoystickUltil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveWithJoysticks extends Command{
	
	private Drivetrain drivetrain;
	
	public MoveWithJoysticks(){
		requires(Robot.drivetrain);
		this.drivetrain = Robot.drivetrain;
		
	}
	
	protected void initialize(){
		
	}
	
	protected void execute(){
		drivetrain.setLeftPowers(JoystickUltil.getLeftYAxisNormalized());
   		drivetrain.setRightPowers(JoystickUltil.getRightYAxisNormalized());
	}

	@Override
	protected boolean isFinished() {

		return false;
	}
}
