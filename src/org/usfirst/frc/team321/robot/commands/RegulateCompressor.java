package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RegulateCompressor extends Command{

	public RegulateCompressor(){
		requires(Robot.pneumatics);
	}
	
	protected void initialize(){	
	}
	
	protected void execute(){
		Robot.pneumatics.regulateCompressor();
	}
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end(){
	}
	
	protected void interrupted(){
	}
}
