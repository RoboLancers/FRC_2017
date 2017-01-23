package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.MoveWithJoysticks;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
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
	
public Drivetrain () {
	
	super("Drive Train");
	
	leftFront = new CANTalon(RobotMap.LEFT_FRONT_MOTOR);
	leftBack = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
	
	rightFront = new CANTalon(RobotMap.RIGHT_FRONT_MOTOR);
	rightBack = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
	
	leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B);
	rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B);
	
	
	rightEncoder.reset();
	leftEncoder.reset();
	
}
	

	public void initDefaultCommand() {
	
		// Set the default command for a subsystem here.
		setDefaultCommand(new MoveWithJoysticks());
	}
	
	public void setLeftPowers(double power){
		
		//Easily control the power of robot by mutiplying it by a value.
		//Extra seive just in case the flour still has lumps. Makes flour FLUFFY!
		
		power = power * 1.0;
		
		if(Math.abs(power)<=1){
			
			leftFront.set(power);
			leftBack.set(power);
			
		}else{
			
			leftFront.set(power/power);
			leftBack.set(power/power);
		}
		
	}
	
	public void setRightPowers(double power){
		
		power = power * 1.0;
		
		if(Math.abs(power)<=1){
			
			rightFront.set(power);
			rightBack.set(power);
			
		}else{
			
			rightFront.set(power/power);
			rightBack.set(power/power);
		}
	}
	
	public void setAllPowers(double power){
		setLeftPowers(power);
		setRightPowers(-power);
	}
	
	    }


















