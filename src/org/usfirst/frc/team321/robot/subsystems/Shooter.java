package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public CANTalon shootMotorLeft, shootMotorRight;
	public Encoder shootEnc;
	
	public static final double boilHeight = 2.0938;
	public static final double RPM = 18730.0;
	public static final double gravity = 9.80665;
	
	public Shooter(){
		shootMotorLeft = new CANTalon(RobotMap.SHOOT_MOTOR_A);
		shootMotorRight = new CANTalon(RobotMap.SHOOT_MOTOR_B);
		shootEnc = new Encoder(RobotMap.SHOOT_ENCODER_A, RobotMap.SHOOT_ENCODER_B);
		shootEnc.reset();
		shootMotorLeft.setVoltageRampRate(10);
		shootMotorRight.setVoltageRampRate(10);
	}
	
	public double calcVelocity(double dist, double angle){
		angle = Math.toRadians(angle - 0.85);
		double xdist = (dist + 0.65) * Math.cos(angle);
		return Math.sqrt((gravity * (xdist * xdist))/(2.0 * Math.cos(angle) * Math.cos(angle) * (boilHeight - Math.tan(angle) * xdist)));
	}
	
	public void setShooter(double power){
		shootMotorLeft.set(RobotUtil.range(-power, -1, 1));
		shootMotorRight.set(RobotUtil.range(-power, -1, 1));
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
