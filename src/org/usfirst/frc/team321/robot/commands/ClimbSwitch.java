package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbSwitch extends Command{

	private boolean hasFinished = true;
	
	public ClimbSwitch(){
		requires(Robot.climber);
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
    protected void execute() {
    	if(Robot.climber.leftClimberToggle.get() == DoubleSolenoid.Value.kForward && 
    			Robot.climber.rightClimberToggle.get() == DoubleSolenoid.Value.kForward){
    		Robot.climber.leftClimberToggle.set(DoubleSolenoid.Value.kReverse);
    		Robot.climber.rightClimberToggle.set(DoubleSolenoid.Value.kReverse);
    	}else{
    		Robot.climber.leftClimberToggle.set(DoubleSolenoid.Value.kForward);
    		Robot.climber.rightClimberToggle.set(DoubleSolenoid.Value.kForward);
    	}
    	
    	hasFinished = true;
    }
	
	@Override
	protected boolean isFinished() {
		return hasFinished;
	}
	
	protected void end(){
	}
	
	protected void interrupted(){
	}

}
