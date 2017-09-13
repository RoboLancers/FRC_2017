package org.usfirst.frc.team321.robot.commands;

import static org.usfirst.frc.team321.robot.Robot.drivetrain;

import org.usfirst.frc.team321.robot.OI;
import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain.DriveMode;
import org.usfirst.frc.team321.robot.utilities.JoystickUtil;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;
import org.usfirst.frc.team321.robot.utilities.XBoxController;

import edu.wpi.first.wpilibj.command.Command;

public class UseDriveTrain extends Command{
	
	private boolean hasFinished = false;
	private double[] motorspeed;
	private boolean isRCDrive = true;
	private boolean bCurrState, bPrevState;
	
	public UseDriveTrain(){
		requires(Robot.drivetrain);
		Drivetrain.driveMode = DriveMode.DRIVING;
		hasFinished = false;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		
		bCurrState = JoystickUtil.isButtonPressed(OI.xboxController, XBoxController.Y_ID);
	    if ((bCurrState == true) && (bCurrState != bPrevState)) {
	        isRCDrive = !isRCDrive;
	    }
	    bPrevState = bCurrState;
		
	    if (isRCDrive) {
			motorspeed = RobotUtil.arcadeDrive(OI.xboxController.getLeftYAxisValue(), OI.xboxController.getRightXAxisValue());
	    } else {
	    	motorspeed[0] = -OI.xboxController.getLeftYAxisNormalized();
	    	motorspeed[1] = -OI.xboxController.getRightYAxisNormalized();
	    }
	    
		switch(Drivetrain.driveMode){
			case DRIVING:
				drivetrain.setLeftPowers(motorspeed[0]);
		   		drivetrain.setRightPowers(motorspeed[1]);
				
		   		break;
		   		
			case CLIMBING:
				if (OI.xboxController.getRawLeftYAxisValue() <= -0.03 || OI.xboxController.getRawRightXAxisValue() >= 0.3) {
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
		    	} else {
		    		Robot.drivetrain.setAllPowers(0);
		    	}
		    	
				break;
		   		
			default:
				drivetrain.setLeftPowers(-OI.xboxController.getLeftYAxisNormalized());
		   		drivetrain.setRightPowers(-OI.xboxController.getRightYAxisNormalized());
		   		break;
		}
   		
		
		if (Robot.sensors.isTrackingDistance() && Robot.sensors.hasDroveDistance(0.5)) {
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
