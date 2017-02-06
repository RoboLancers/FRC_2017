package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class GearEjector extends Command{

		private boolean hasFinished;
		
		public GearEjector(){
			requires(Robot.geardoor);
		}

		protected void initialize(){
			hasFinished = false;
		}
		
	    protected void execute() {
	    	if(Robot.geardoor.leftGearEjector.get() == DoubleSolenoid.Value.kForward && 
	    			Robot.geardoor.rightGearEjector.get() == DoubleSolenoid.Value.kForward){
	    		Robot.geardoor.leftGearEjector.set(DoubleSolenoid.Value.kReverse);
	    		Robot.geardoor.rightGearEjector.set(DoubleSolenoid.Value.kReverse);
	    	}else{
	    		Robot.geardoor.leftGearEjector.set(DoubleSolenoid.Value.kForward);
	    		Robot.geardoor.rightGearEjector.set(DoubleSolenoid.Value.kForward);
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
