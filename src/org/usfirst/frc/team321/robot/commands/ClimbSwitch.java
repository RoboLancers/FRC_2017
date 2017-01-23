package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbSwitch extends Command{

	private boolean hasFinished = false;
	private Pneumatics pneumatics;
	
	public ClimbSwitch(){
		requires(Robot.pneumatics);
		this.pneumatics = Robot.pneumatics;
	}
	
	protected void initialize(){
	}
	
    protected void execute() {
    	if(pneumatics.leftClimberToggle.get() == DoubleSolenoid.Value.kForward && 
    			pneumatics.rightClimberToggle.get() == DoubleSolenoid.Value.kForward){
    		pneumatics.leftClimberToggle.set(DoubleSolenoid.Value.kReverse);
    		pneumatics.rightClimberToggle.set(DoubleSolenoid.Value.kReverse);
    	}else{
    		pneumatics.leftClimberToggle.set(DoubleSolenoid.Value.kForward);
    		pneumatics.rightClimberToggle.set(DoubleSolenoid.Value.kForward);
    	}
    	
    	hasFinished = true;
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
