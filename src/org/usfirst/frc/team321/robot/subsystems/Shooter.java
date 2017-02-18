package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public CANTalon shootMotorLeft, shootMotorRight;
	
	public static final double boilHeight = 2.0938;
	public static final double shooterAngle = 89.15;
	public static final double RPM = 18730.0;
	public static final double gravity = 9.80665;
	
	public Shooter() {
		shootMotorLeft = new CANTalon(RobotMap.SHOOT_MOTOR_A);
		shootMotorRight = new CANTalon(RobotMap.SHOOT_MOTOR_B);
		
		shootMotorLeft.setVoltageRampRate(5);
		shootMotorRight.setVoltageRampRate(5);
	}
	
	public double calcVelocity(double dist) {
		double xdist = (dist) * Math.cos(shooterAngle);
		return Math.sqrt((gravity * (xdist * xdist))/(2.0 * Math.cos(shooterAngle) * Math.cos(shooterAngle) * (boilHeight - Math.tan(shooterAngle) * xdist)));
	}
	
	public void setShooter(double power) {
		shootMotorLeft.set(RobotUtil.range(power, -1, 1));
		shootMotorRight.set(RobotUtil.range(power, -1, 1));
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}