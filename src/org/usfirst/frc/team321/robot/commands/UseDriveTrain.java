package org.usfirst.frc.team321.robot.commands;

import static org.usfirst.frc.team321.robot.Robot.drivetrain;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain.DriveMode;
import org.usfirst.frc.team321.robot.utilities.JoystickUtil;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

public class UseDriveTrain extends Command{
	
	private boolean hasFinished = false;
	private double[] motorspeed;
	
	public UseDriveTrain(){
		requires(Robot.drivetrain);
		Drivetrain.driveMode = DriveMode.DRIVING;
		hasFinished = false;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		motorspeed = RobotUtil.arcadeDrive(JoystickUtil.getLeftYAxisValue(), JoystickUtil.getRightXAxisValue());
		switch(Drivetrain.driveMode){
			case DRIVING:
				/*
				if (Math.abs(Robot.drivetrain.getLeftRPM()) > Drivetrain.targetRPM && 
						Math.abs(Robot.drivetrain.getRightRPM()) > Drivetrain.targetRPM &&
						Math.signum(JoystickUtil.getLeftYAxisValue()) == Math.signum(JoystickUtil.getRightYAxisValue())) {
					Robot.gearshifter.setHighGear();
				} else {
					Robot.gearshifter.setLowGear();
				}
				
				if (Robot.climber.isClimbing()) {
					drivetrain.setLeftPowers(-Math.abs(JoystickUtil.getLeftYAxisNormalized()));
			   		drivetrain.setRightPowers(-Math.abs(JoystickUtil.getRightYAxisNormalized()));
				} else {
					drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
			   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
				}
		   		break;
		   		*/
				/*
				drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
		   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
		   		*/
				drivetrain.setLeftPowers(motorspeed[0]);
		   		drivetrain.setRightPowers(motorspeed[1]);
				
		   		break;
		   		
			case CLIMBING:
				if (JoystickUtil.getRawLeftYAxisValue() <= -0.03 || JoystickUtil.getRawRightXAxisValue() >= 0.3) {
					Robot.climber.engageClimber();
				} else {
					Robot.climber.disengageClimber();
				}

				drivetrain.setLeftPowers(-motorspeed[1]);
				drivetrain.setRightPowers(-motorspeed[0]);
			   	break;
			
			case AUTO_ADJUST:
				if (Robot.camera.gearTargetDetected()) {
					motorspeed = RobotUtil.moveToTarget(0.55, Robot.camera.gearTargetAngle(), 0);
					
		    		Robot.drivetrain.setLeftPowers(motorspeed[1]);
		    		Robot.drivetrain.setRightPowers(motorspeed[0]);
		    		
		    		if (Robot.sensors.isGearLoaded() && Robot.sensors.isGearPenetrated()) {
		    			Robot.gearholder.openDoor();
		    			Robot.sensors.startTrackingDistance();
		    		}
		    	} /*else if (Robot.camera.boilerTargetDetected()){
					motorspeed = RobotUtil.moveToTarget(0, Robot.camera.boilerTargetAngle(), 0);
					
					Robot.drivetrain.setLeftPowers(motorspeed[0]);
		    		Robot.drivetrain.setRightPowers(motorspeed[1]);
		    	} */else {
		    		Robot.drivetrain.setAllPowers(0);
		    	}
		    	
				break;
				
		   		
		   		
			default:
				drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
		   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
		   		break;
		}
   		
		
		if (Robot.sensors.isTrackingDistance() && Robot.sensors.hasDroveDistance(0.5)) {
			Robot.sensors.stopTrackingDistance();
			Robot.gearholder.closeDoor();
		}
		
		/*
		if (Robot.climber.isClimbing()) {
			if (JoystickUtil.getLeftYAxisValue() > 0.1) {
				Climber.climberToggle.set(DoubleSolenoid.Value.kReverse);
			} else if (JoystickUtil.getLeftYAxisValue() < -0.1) {
				Climber.climberToggle.set(DoubleSolenoid.Value.kForward);
			}
			
			drivetrain.setLeftPowers(-JoystickUtil.getRightYAxisNormalized());
		   	drivetrain.setRightPowers(-JoystickUtil.getLeftYAxisNormalized());
		} else {
			drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
	   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
		}
		*/
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
