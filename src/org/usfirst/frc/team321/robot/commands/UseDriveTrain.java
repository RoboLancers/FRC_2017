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
	private double currentAngle = 0;
	private double direction = 0;
	
	public UseDriveTrain(){
		requires(Robot.drivetrain);
		Drivetrain.driveMode = DriveMode.DRIVING;
		hasFinished = false;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		switch(Drivetrain.driveMode){
			case DRIVING:
				drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
		   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
		   		break;
		   		
			case CLIMBING:
				if (JoystickUtil.getLeftYAxisValue() > 0.1) {
					Climber.climberToggle.set(DoubleSolenoid.Value.kReverse);
				} else if (JoystickUtil.getLeftYAxisValue() < -0.1) {
					Climber.climberToggle.set(DoubleSolenoid.Value.kForward);
				}
				
				drivetrain.setLeftPowers(-JoystickUtil.getRightYAxisNormalized());
			   	drivetrain.setRightPowers(-JoystickUtil.getLeftYAxisNormalized());
				break;
			
			case AUTO_ADJUST:
				if (Robot.camera.gearTargetDetected()) {
					currentAngle = Robot.camera.gearTargetAngle();
					
		    		direction = Math.signum(currentAngle);
					
		    		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(0.55, RobotUtil.squareAndKeepSign(currentAngle), 0)[0]);
		    		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(0.55, RobotUtil.squareAndKeepSign(currentAngle), 0)[1]);
		    		
		    		if (Robot.sensors.isGearLoaded() && Robot.sensors.isGearPenetrated()) {
		    			Robot.gearholder.openDoor();
		    			Robot.sensors.startTracking();
		    		}
		    	} else if (Robot.camera.boilerTargetDetected()) {
		    		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(0, Robot.camera.boilerTargetAngle(), 0)[0] / 3);
		    		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(0, Robot.camera.boilerTargetAngle(), 0)[1] / 3);
		    	} else if (!Robot.sensors.isTracking) { 
			    	Robot.drivetrain.setLeftPowers(-direction * 0.4);
			    	Robot.drivetrain.setRightPowers(direction * 0.4);
		    	} else {
		    		Robot.drivetrain.setAllPowers(0);
		    	}
		    	
				break;
				
			default:
				drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
		   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
		   		break;
			
		}
		
		if (Robot.sensors.hasDroveDistance(3)) {
			Robot.gearholder.closeDoor();
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
