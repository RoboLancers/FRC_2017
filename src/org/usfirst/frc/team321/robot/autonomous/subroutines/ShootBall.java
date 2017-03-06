package org.usfirst.frc.team321.robot.autonomous.subroutines;

import static org.usfirst.frc.team321.robot.Robot.camera;
import static org.usfirst.frc.team321.robot.Robot.shooter;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {

	private Timer timer = new Timer();
	private double time = 0;
	
	public ShootBall(double time) {
		requires(Robot.shooter);
		this.time = time;
    }

    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	try {
			shooter.setShooterRPM(shooter.velocityToRPM(shooter.calcVelocity(camera.boilerTargetDistance())));
		} catch (Exception e) {
			shooter.setShooterRPM(0);
		}
    }

    protected void end() {
		shooter.setShooterRPM(0);
    }

    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return timer.get() > time;
	}
}
