package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.OI;
import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.JoystickUtil;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.command.Command;

/**
 *The shooter acquires mid-game information about the components of distance and the angle
 *from the boiler. They gain this information through OpenCV code, using the robot's camera
 *in the process 
 */
public class UseShooter extends Command {

	private boolean hasFinished;
	
	public UseShooter(){
		requires(Robot.shooter);
		hasFinished = false;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		Robot.shooter.setShooter(RobotUtil.range(OI.flightStick.getRudderAxis(), -1, 1));
	}

	protected void end(){
		Robot.shooter.setShooter(0);
		hasFinished = true;
	}
	
    protected void interrupted() {
		Robot.shooter.setShooter(0);
    	hasFinished = true;
    }
    
	@Override
	protected boolean isFinished() {
		return hasFinished;
	}
}
