package org.usfirst.frc.team321.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Drivetrain Motors
	public static final int LEFT_FRONT_MOTOR = 1;
	public static final int LEFT_BACK_MOTOR = 2;
	
	public static final int RIGHT_FRONT_MOTOR = 3;
	public static final int RIGHT_BACK_MOTOR = 4;
	
	//Subsystem Motors
	public static final int CONVEYOR_MOTOR = 5;
	public static final int SHOOT_MOTOR_A = 6;
	public static final int SHOOT_MOTOR_B = 7;
	public static final int INDEXER_MOTOR = 8;
	
	//Motor Encoders
	public static final int LEFT_ENCODER_A = 1;
	public static final int LEFT_ENCODER_B = 2;
	
	public static final int RIGHT_ENCODER_A = 3;
	public static final int RIGHT_ENCODER_B = 4;
	
	public static final int SHOOT_ENCODER_A = 6;
	public static final int SHOOT_ENCODER_B = 7;
	
	public static final int COMPRESSOR = 0;
	
	public static final int BUMPER_TOUCH = 1;
}
