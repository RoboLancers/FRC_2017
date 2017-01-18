package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;

import com.ctre.CANTalon;
//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public Encoder leftEncoder;
	public Encoder rightEncoder; 
	public CANTalon leftFront, leftBack, rightFront, rightBack;
	//public AHRS navX; 
	
public Drivetrain () {
	
	leftFront = new CANTalon(RobotMap.LEFT_FRONT_MOTOR);
	leftBack = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
	
	rightFront = new CANTalon(RobotMap.RIGHT_FRONT_MOTOR);
	rightBack = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
	
	leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B);
	rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B);
	
	/*
	navX = new AHRS(SerialPort.Port.kMXP);
	navX.reset();
	navX.resetDisplacement();
	*/
	
	rightEncoder.reset();
	leftEncoder.reset();
	
}
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	/*
	public void setMotorPower(double leftPower, double rightPower) {
		leftFront.set(normalizeMotorValue(leftPower, MIN_POWER, MAX_POWER));
		leftBack.set(normalizeMotorValue(leftPower, MIN_POWER, MAX_POWER));

		rightFront.set(-normalizeMotorValue(rightPower, MIN_POWER, MAX_POWER));
		rightBack.set(-normalizeMotorValue(rightPower, MIN_POWER, MAX_POWER));
	*/
	    }

