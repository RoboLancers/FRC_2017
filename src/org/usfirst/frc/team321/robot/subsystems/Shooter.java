package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public CANTalon shootMotorLeft, shootMotorRight;
	
	public static final double ticksPerRevolution = 1024;
	public static final double wheelCircumferance = 0.1016 * Math.PI;
	
	public static final double boilHeight = 2.0938;
	public static final double shooterAngle = 77;
	public static final double gravity = -9.80665;
	
	public Shooter() {
		shootMotorLeft = new CANTalon(RobotMap.SHOOT_MOTOR_A);
		shootMotorRight = new CANTalon(RobotMap.SHOOT_MOTOR_B);
		/*
		shootMotorLeft.changeControlMode(TalonControlMode.Speed);
		shootMotorRight.changeControlMode(TalonControlMode.Follower);
		shootMotorRight.set(shootMotorLeft.getDeviceID());
		*/
		shootMotorLeft.setVoltageRampRate(4);
		shootMotorRight.setVoltageRampRate(4);
	}

	public double velocityToRPM(double velocity) {
	    return velocity * 60 / (2 * Math.PI * 0.0508);
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
	
	public void setShooterRPM(double power) {
		try {
			shootMotorLeft.set(power);
			shootMotorRight.set(power);
		} catch (Exception e) {
			shootMotorLeft.set(0);
			shootMotorRight.set(0);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}