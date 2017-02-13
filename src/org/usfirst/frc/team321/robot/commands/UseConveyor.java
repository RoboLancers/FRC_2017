package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UseConveyor extends Command {

	private boolean hasFinished;
	private double conveyVal;
	
	public UseConveyor(){
		requires(Robot.conveyor);
		hasFinished = false;
		conveyVal = 1;
	}
	
	public UseConveyor(double val){
		requires(Robot.conveyor);
		hasFinished = false;
		conveyVal = val;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		Robot.conveyor.intakeMotor.set(-conveyVal);
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
