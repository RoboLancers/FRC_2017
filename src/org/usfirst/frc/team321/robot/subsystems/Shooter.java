package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public CANTalon shootMotorLeft, shootMotorRight;
	public Encoder shooterEncoder;
	
	public static final double ticksPerRevolution = 1024;
	public static final double wheelCircumferance = 0.1016 * Math.PI;
	public static final double distancePerPulse = wheelCircumferance / ticksPerRevolution; 
	
	public static final double boilHeight = 2.0938;
	public static final double shooterAngle = 89.15;
	public static final double RPM = 18730.0;
	public static final double gravity = -9.80665;
	
	public Shooter() {
		shootMotorLeft = new CANTalon(RobotMap.SHOOT_MOTOR_A);
		shootMotorRight = new CANTalon(RobotMap.SHOOT_MOTOR_B);
		
		//shooterEncoder = new Encoder(1, 2); //Encoder has 2 ports fsr kill me
		
		shootMotorLeft.setVoltageRampRate(6);
		shootMotorRight.setVoltageRampRate(6);
		
		//shooterEncoder.setDistancePerPulse(distancePerPulse);
	}

	public double getRPM() {
		return shooterEncoder.getRate() * 60 / wheelCircumferance;
	}
	
	public void setRPM(double rpm) {
		setShooter(RobotUtil.moveToTarget(0, getRPM(), rpm)[1]);
	}
	
	public double calcVelocity(double dist) {
		double xdist = (dist) * Math.cos(shooterAngle);
		return Math.sqrt((gravity * (xdist * xdist))/(2.0 * Math.cos(shooterAngle) * Math.cos(shooterAngle) * (boilHeight - Math.tan(shooterAngle) * xdist)));
	}
	
	public void setShooter(double power) {
		try {
			shootMotorLeft.set(RobotUtil.range(power, -1, 1));
			shootMotorRight.set(RobotUtil.range(power, -1, 1));
		} catch (Exception e) {
			shootMotorLeft.set(0);
			shootMotorRight.set(0);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}