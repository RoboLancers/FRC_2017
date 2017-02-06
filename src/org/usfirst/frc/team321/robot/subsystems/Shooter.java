package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.BallShoot;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public CANTalon[] shootMotor = new CANTalon[2];
	public CANTalon indexer;
	public Encoder shootEnc;
	
	private double[] encoderCount = new double[20];
	
	public static final double boilHeight = 2.4638;
	public static final double RPM = 18730.0;
	public static final double gravity = 9.80665;
	private double tolerance = 1440;
	
	public Shooter(){
		shootMotor[0] = new CANTalon(RobotMap.SHOOT_MOTOR_A);
		shootMotor[1] = new CANTalon(RobotMap.SHOOT_MOTOR_B);
		indexer = new CANTalon(RobotMap.INDEXER_MOTOR);
		shootEnc = new Encoder(RobotMap.SHOOT_ENCODER_A, RobotMap.SHOOT_ENCODER_B);
		shootEnc.reset();
		for(int x = 0; x < encoderCount.length; x++) {
			encoderCount[x] = 0;
		}
		shootMotor[0].setVoltageRampRate(8);
		shootMotor[1].setVoltageRampRate(8);
		indexer.setVoltageRampRate(8);
	}
	
	public double setVelocity(double dist, double angle){
		angle = Math.toRadians(angle);
		double xdist = dist * Math.cos(angle);
		double ydist = dist * Math.sin(angle);
		return Math.sqrt((gravity * (xdist * xdist))/(2.0 * Math.cos(angle) * Math.cos(angle) * (ydist - Math.tan(angle) * xdist)));
	}
	
	public void clampVelocity(double power){
		shootMotor[0].set(RobotUtil.range(power, -1, 1));
		shootMotor[1].set(RobotUtil.range(power, -1, 1));
		indexer.set(RobotUtil.range(power * 0.8, -1, 1));
	}
	
	public boolean isJammed() {
		encoderCount[encoderCount.length-1] = shootEnc.get();
		
		for(int x = 0; x < encoderCount.length - 1; x++) {
			encoderCount[x] = encoderCount[x+1];
		}
		
		if (Math.abs(encoderCount[encoderCount.length-1] - encoderCount[0]) < tolerance) {
			return true;
		}
		
		return false;
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
