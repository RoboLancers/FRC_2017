package org.usfirst.frc.team321.robot.commands;

import static org.usfirst.frc.team321.robot.Robot.drivetrain;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Climber;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain.DriveMode;
import org.usfirst.frc.team321.robot.utilities.JoystickUtil;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class UseDriveTrain extends Command{
	
	private boolean hasFinished = false;
	private double[] motorSpeed;
	private double currentAngle = 0;
	
	public UseDriveTrain(){
		requires(Robot.drivetrain);
		Drivetrain.driveMode = DriveMode.DRIVING;
		hasFinished = false;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		/*
		switch(Drivetrain.driveMode){
			case DRIVING:
				
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
			
			case AUTO_ADJUST:
				if (Robot.camera.gearTargetDetected()) {
					currentAngle = Robot.camera.gearTargetAngle();
					motorSpeed = RobotUtil.moveToTarget(0.55, RobotUtil.sqrtKeepSign(currentAngle), 0);
					
		    		Robot.drivetrain.setLeftPowers(motorSpeed[0]);
		    		Robot.drivetrain.setRightPowers(motorSpeed[1]);
		    		
		    		if (Robot.sensors.isGearLoaded() && Robot.sensors.isGearPenetrated()) {
		    			Robot.gearholder.openDoor();
		    			Robot.sensors.startTrackingDistance();
		    		}
		    	} else if (Robot.camera.boilerTargetDetected()){
					currentAngle = Robot.camera.boilerTargetAngle();
					motorSpeed = RobotUtil.moveToTarget(0, RobotUtil.sqrtKeepSign(currentAngle), 0);
					
					Robot.drivetrain.setLeftPowers(motorSpeed[0]);
		    		Robot.drivetrain.setRightPowers(motorSpeed[1]);
		    	} else {
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
		*/
		
		if (Robot.climber.isClimbing()) {
			drivetrain.setLeftPowers(-Math.abs(JoystickUtil.getLeftYAxisNormalized()));
	   		drivetrain.setRightPowers(-Math.abs(JoystickUtil.getRightYAxisNormalized()));
		} else {
			drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
	   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
		}
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
