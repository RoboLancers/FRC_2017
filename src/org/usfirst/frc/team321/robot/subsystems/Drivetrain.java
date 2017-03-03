package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.UseDriveTrain;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *  Notes: Right Side is reversed
 */
public class Drivetrain extends Subsystem {
	
	public CANTalon leftFront, leftBack, rightFront, rightBack;
	public Encoder leftSide, rightSide;
	public static DriveMode driveMode;
	
    public final double wheelDiameter = 1.2192;
  
    public final double wheelCircumference = wheelDiameter * Math.PI;

    public final double ticksPerRevolution = 1440;
    
    public final double ticksPerMeter = ticksPerRevolution / wheelCircumference;
	
	public Drivetrain () {
		super("Drive Train");
		
		leftFront = new CANTalon(RobotMap.LEFT_FRONT_MOTOR);
		leftBack = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
		
		rightFront = new CANTalon(RobotMap.RIGHT_FRONT_MOTOR);
		rightBack = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
		
		//leftSide.setDistancePerPulse(0.319024 / ticksPerRevolution);
		//rightSide.setDistancePerPulse(0.319024 / ticksPerRevolution);
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new UseDriveTrain());
	}
	
	public static enum DriveMode {
		DRIVING, AUTO_ADJUST, CLIMBING;
	} 
	
	public void setLeftPowers(double power){
		power = power * 1.0;
		
		leftFront.set(RobotUtil.range(power, -1, 1));
		leftBack.set(RobotUtil.range(power, -1, 1));
	}
	
	public void setRightPowers(double power){
		power = power * -1.0;
		
		rightFront.set(RobotUtil.range(power, -1, 1));
		rightBack.set(RobotUtil.range(power, -1, 1));
	}
	
	public void setAllPowers(double power){
		setLeftPowers(power);
		setRightPowers(power);
	}

	public void stopMotors(){
		setAllPowers(0);
	}
	
	public int distanceToEncTicks(double meters) {
		return (int)(meters / ticksPerMeter);
	}
    
	public void resetEncoders() {
		leftFront.setPosition(0);
		leftBack.setPosition(0);
		rightFront.setPosition(0);
		rightBack.setPosition(0);
	}
	
	public void driveDistance(double meters) {
		leftFront.setEncPosition(distanceToEncTicks(meters));
		leftBack.setEncPosition(distanceToEncTicks(meters));
		rightFront.setEncPosition(distanceToEncTicks(meters));
		rightBack.setEncPosition(distanceToEncTicks(meters));
	}
	
	public double getRightDistance() {
		return rightFront.getEncPosition() / ticksPerMeter;
	}
}