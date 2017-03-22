package org.usfirst.frc.team321.robot.autonomous.subroutines;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnUntilPeg extends Command {
	private double power;
	private double seconds = 6;
	private boolean isLeft;
	Timer timer = new Timer();
	
	//Is the peg on the left?
	public TurnUntilPeg(double power, double timeout, boolean isLeft) {
		requires(Robot.drivetrain);
		Robot.gearholder.closeDoor();
		this.power = power;
		seconds = timeout;
		this.isLeft = isLeft;
    }

    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    protected void execute() {
    	Robot.drivetrain.setLeftPowers(power * (isLeft ? -1 : 1));
    	Robot.drivetrain.setRightPowers(power * (isLeft ? 1 : -1));
    }

    protected void end() {
    	Robot.drivetrain.setAllPowers(0);
    }

    protected void interrupted() {
    	end();
    }

	@Override
	protected boolean isFinished() {
		return Robot.camera.gearTargetDetected() || timer.get() > seconds;
	}
}
