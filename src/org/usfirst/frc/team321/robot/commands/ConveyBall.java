package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Conveyor.IntakeValues;

import edu.wpi.first.wpilibj.command.Command;

public class ConveyBall extends Command {

	boolean hasFinished = false;
	
	public ConveyBall(){
		requires(Robot.conveyor);
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		Robot.conveyor.intakeMotor.set(1);
	}
	
	protected void end(){
		Robot.conveyor.intakeMotor.set(0);
		hasFinished = true;
	}
	
    protected void interrupted() {
		Robot.conveyor.intakeMotor.set(0);
    	hasFinished = true;
    }
	
	@Override
	protected boolean isFinished() {
		return hasFinished;
	}

}
