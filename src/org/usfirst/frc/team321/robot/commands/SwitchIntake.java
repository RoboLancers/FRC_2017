package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class SwitchIntake extends Command {

private boolean hasFinished;
	
	public SwitchIntake(){
		requires(Robot.intakeswitch);
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute() {
    	if(Robot.intakeswitch.leftIntakeSwitch.get() == DoubleSolenoid.Value.kForward && 
    			Robot.intakeswitch.rightIntakeSwitch.get() == DoubleSolenoid.Value.kForward){
    		Robot.intakeswitch.leftIntakeSwitch.set(DoubleSolenoid.Value.kReverse);
    		Robot.intakeswitch.rightIntakeSwitch.set(DoubleSolenoid.Value.kReverse);
    	}else{
    		Robot.intakeswitch.leftIntakeSwitch.set(DoubleSolenoid.Value.kForward);
    		Robot.intakeswitch.rightIntakeSwitch.set(DoubleSolenoid.Value.kForward);
    	}
    	
    	hasFinished = true;
    }
	
	@Override
	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end(){
		hasFinished = true;
	}
	
	protected void interrupted(){
		hasFinished = true;
	}
}
