package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *The shooter acquires mid-game information about the components of distance and the angle
 *from the boiler. They gain this information through OpenCV code, using the robot's camera
 *in the process 
 */
public class BallShoot extends Command{

	public Shooter shootMotor;
	public static boolean hasFinished = true;
	
	public BallShoot(){
		requires(Robot.shooter);
//		this.shootMotor = shootMotor;
	}
	
	protected void initialize(){
	}
	
	protected void execute(double power){
		//OpenCV Code for Distances and Angles
		//shootMotor.setVelocity(xdist, ydist, angle);
		//shootMotor.clampVelocity(power);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
