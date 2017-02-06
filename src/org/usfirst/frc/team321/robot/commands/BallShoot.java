package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Shooter;
import org.usfirst.frc.team321.robot.subsystems.Conveyor.IntakeValues;

import edu.wpi.first.wpilibj.command.Command;

/**
 *The shooter acquires mid-game information about the components of distance and the angle
 *from the boiler. They gain this information through OpenCV code, using the robot's camera
 *in the process 
 */
public class BallShoot extends Command{

	boolean hasFinished = false;
	
	public BallShoot(){
		requires(Robot.shooter);
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		//OpenCV Code for Distances and Angles
		Robot.shooter.clampVelocity(0.8);
		
		hasFinished = true;
	}
	
	@Override
	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end(){
		hasFinished = true;
	}
	
    protected void interrupted() {
    	hasFinished = true;
    }
}
