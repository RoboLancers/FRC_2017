package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import static org.usfirst.frc.team321.robot.Robot.shooter;
import static org.usfirst.frc.team321.robot.Robot.camera;

import edu.wpi.first.wpilibj.command.Command;

/**
 *The shooter acquires mid-game information about the components of distance and the angle
 *from the boiler. They gain this information through OpenCV code, using the robot's camera
 *in the process 
 */
public class UseShooterRPM extends Command {

	private boolean hasFinished;
	private double shooterVal = 0;
	
	/*
	 * Default
	 */
	public UseShooterRPM(){
		requires(Robot.shooter);
		hasFinished = false;
		shooterVal = 0;
	}
	
	public UseShooterRPM(double val){
		requires(Robot.shooter);
		hasFinished = false;
		shooterVal = val;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	protected void execute(){
		if (shooterVal != 0) {
			shooter.setShooterRPM(shooterVal);
		} else {
			try {
				shooter.setShooterRPM(shooter.velocityToRPM(shooter.calcVelocity(camera.boilerTargetDistance())));
			} catch (Exception e) {
				shooter.setShooterRPM(0);
			}
		}
	}

	protected void end(){
		Robot.shooter.setShooterRPM(0);
		hasFinished = true;
	}
	
    protected void interrupted() {
		Robot.shooter.setShooterRPM(0);
    	hasFinished = true;
    }
    
	@Override
	protected boolean isFinished() {
		return hasFinished;
	}
}
