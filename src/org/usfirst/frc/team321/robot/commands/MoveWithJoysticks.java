package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import static org.usfirst.frc.team321.robot.Robot.drivetrain;
import org.usfirst.frc.team321.robot.utilities.JoystickUtil;

import edu.wpi.first.wpilibj.command.Command;

public class MoveWithJoysticks extends Command{
	
	private boolean hasFinished = false;
	
	public MoveWithJoysticks(){
		requires(Robot.drivetrain);
		hasFinished = false;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		drivetrain.setLeftPowers(JoystickUtil.getLeftYAxisNormalized());
   		drivetrain.setRightPowers(JoystickUtil.getRightYAxisNormalized());
	}

	@Override
	protected boolean isFinished() {
		return hasFinished;
	}

	@Override
	protected void end() {
		hasFinished = true;
	}

	@Override
	protected void interrupted() {
		hasFinished = true;
	}
	
	
}
