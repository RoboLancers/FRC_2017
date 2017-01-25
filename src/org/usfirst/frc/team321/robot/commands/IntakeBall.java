package org.usfirst.frc.team321.robot.commands;

import org.usfirst.frc.team321.robot.Robot;
import org.usfirst.frc.team321.robot.subsystems.Intake.IntakeValues;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeBall extends Command {

	public IntakeValues intakeValue;
	public static boolean hasFinished = true;
	
	public IntakeBall(IntakeValues intakeValue){
		requires(Robot.shooter);
		this.intakeValue = intakeValue;
	}
	
	protected void initialize(){
		hasFinished = false;
	}
	
	public void setPower(double power){
		Robot.shooter.clampVelocity(power);
	}
	
	protected void execute(){
		if(intakeValue == IntakeValues.INTAKE){
			setPower(IntakeValues.INTAKE.getValue());	
		}else if(intakeValue == IntakeValues.OUTTAKE){
			setPower(IntakeValues.OUTTAKE.getValue());
		}else{
			setPower(IntakeValues.STOP.getValue());
		}
	}
	
	protected void end(){
		setPower(IntakeValues.STOP.getValue());
	}
	
    protected void interrupted() {
    	setPower(IntakeValues.STOP.getValue());
    	hasFinished = true;
    }
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
