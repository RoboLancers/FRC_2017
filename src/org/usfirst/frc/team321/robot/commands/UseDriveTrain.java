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
				if (Math.abs(Robot.drivetrain.getLeftRPM()) > Drivetrain.targetRPM && 
						Math.abs(Robot.drivetrain.getRightRPM()) > Drivetrain.targetRPM &&
						Math.signum(JoystickUtil.getLeftYAxisValue()) == Math.signum(JoystickUtil.getRightYAxisValue())) {
					Robot.gearshifter.setHighGear();
				} else {
					Robot.gearshifter.setLowGear();
				}
				
				drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
		   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
		   		break;
			
			case AUTO_ADJUST:
				if (Robot.camera.gearTargetDetected()) {
					currentAngle = Robot.camera.gearTargetAngle();
					
		    		Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(0.55, RobotUtil.squareAndKeepSign(currentAngle), 0)[0]);
		    		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(0.55, RobotUtil.squareAndKeepSign(currentAngle), 0)[1]);
		    		
		    		if (Robot.sensors.isGearLoaded() && Robot.sensors.isGearPenetrated()) {
		    			Robot.gearholder.openDoor();
		    			Robot.sensors.startTrackingDistance();
		    		}
		    	} else if (Robot.camera.boilerTargetDetected()){
					currentAngle = Robot.camera.boilerTargetAngle();
					
					Robot.drivetrain.setLeftPowers(RobotUtil.moveToTarget(0, RobotUtil.squareAndKeepSign(currentAngle), 0)[0]);
		    		Robot.drivetrain.setRightPowers(RobotUtil.moveToTarget(0, RobotUtil.squareAndKeepSign(currentAngle), 0)[1]);
		    	} else {
		    		Robot.drivetrain.setAllPowers(0);
		    	}
		    	
				break;
				
			default:
				drivetrain.setLeftPowers(-JoystickUtil.getLeftYAxisNormalized());
		   		drivetrain.setRightPowers(-JoystickUtil.getRightYAxisNormalized());
		   		break;
			
		}
		
		if (Robot.sensors.isTrackingDistance() && Robot.sensors.hasDroveDistance(1)) {
			Robot.sensors.stopTrackingDistance();
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
