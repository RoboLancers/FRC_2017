package org.usfirst.frc.team321.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Drivetrain Motors
	public static final int LEFT_FRONT_MOTOR = 4;
	public static final int LEFT_BACK_MOTOR = 8;
	
	public static final int RIGHT_FRONT_MOTOR = 6;
	public static final int RIGHT_BACK_MOTOR = 5;
	
	//Subsystem Motors
	public static final int CONVEYOR_MOTOR = 1;
	public static final int SHOOT_MOTOR_A = 2;
	public static final int SHOOT_MOTOR_B = 3;
	public static final int INDEXER_MOTOR = 7;

	//Compressor
	public static final int COMPRESSOR = 0;
	
	//Pneumatics
	public static final int GEARDOOR_FORWARD = 3;
	public static final int GEARDOOR_REVERSE = 4;
	public static final int INTAKE_FORWARD = 2;
	public static final int INTAKE_REVERSE = 5;
	public static final int GEARSHIFTER_FORWARD = 1;
	public static final int GEARSHIFTER_REVERSE = 6; 
	public static final int CLIMBER_FORWARD = 0;
	public static final int CLIMBER_REVERSE = 7;
	
	//Digital Input Sensors
	public static final int BUMPER_TOUCH = 0;
	public static final int IR_SENSOR = 5;
}
