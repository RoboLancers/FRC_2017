package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class UseIndexer extends Command {

private boolean hasFinished;
	
	public UseIndexer(){
		requires(Robot.indexer);
		hasFinished = false;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		Robot.indexer.setIndexer(-1);
	}

	protected void end(){
		Robot.indexer.setIndexer(0);
		hasFinished = true;
	}
	
    protected void interrupted() {
		Robot.indexer.setIndexer(0);
    	hasFinished = true;
    }
    
	@Override
	protected boolean isFinished() {
		return hasFinished;
	}
}
