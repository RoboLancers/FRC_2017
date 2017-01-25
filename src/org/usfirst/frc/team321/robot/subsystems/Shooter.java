package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public CANTalon shootMotor;
	public Encoder shootEnc;
	public static final double RPM = 18730.0;
	public static final double gravity = 9.80665;
	
	public Shooter(){
		shootMotor = new CANTalon(RobotMap.SHOOT_MOTOR);
	}
	
	public static double setVelocity(double xdist, double ydist, double angle){
		double velocity = Math.sqrt((gravity * (xdist * xdist))/(2 * Math.cos(angle) * Math.cos(angle) * (ydist - Math.atan(angle))));
		return velocity;
	}
	
	public void clampVelocity(double power){
		shootMotor.set(Math.signum(power));
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
