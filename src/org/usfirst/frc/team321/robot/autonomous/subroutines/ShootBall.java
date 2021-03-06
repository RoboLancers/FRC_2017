package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;

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
    	Robot.conveyor.intakeMotor.set(1);
    }

    protected void end() {
    	Robot.conveyor.intakeMotor.set(0);
    }

    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return timer.get() > time;
	}
}
