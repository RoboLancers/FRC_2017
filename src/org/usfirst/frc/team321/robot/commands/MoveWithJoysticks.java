package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.utilities.JoystickUtil;

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
		drivetrain.setLeftPowers(JoystickUtil.getLeftYAxisNormalized());
   		drivetrain.setRightPowers(JoystickUtil.getRightYAxisNormalized());
	}

	@Override
	protected boolean isFinished() {

		return false;
	}
}
