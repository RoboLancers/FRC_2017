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
	public Encoder leftEncoder, rightEncoder;
	public static DriveMode driveMode;
	
    public final double wheelDiameter = 0.1016;
    public final double pulsePerRevolution = 256;
    public final double wheelCircumference = wheelDiameter * Math.PI;
    public final double meterPerPulse = wheelCircumference / pulsePerRevolution;
	public static double targetRPM = 500;
    
	public Drivetrain () {
		super("Drive Train");
		
		leftFront = new CANTalon(RobotMap.LEFT_FRONT_MOTOR);
		leftBack = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
		
		rightFront = new CANTalon(RobotMap.RIGHT_FRONT_MOTOR);
		rightBack = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
		
		//leftEncoder = new Encoder(1, 2);
		//rightEncoder = new Encoder(3, 4);
		
		//leftEncoder.setDistancePerPulse(0.319024 / pulsePerRevolution);
		//rightEncoder.setDistancePerPulse(0.319024 / pulsePerRevolution);
		
		//leftEncoder.reset();
		//rightEncoder.reset();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new UseDriveTrain());
	}
	
	public static enum DriveMode {
		DRIVING, AUTO_ADJUST;
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
		return (int)(meters / meterPerPulse);
	}
	
	public void driveDistance(double meters) {
		leftFront.setEncPosition(distanceToEncTicks(meters));
		leftBack.setEncPosition(distanceToEncTicks(meters));
		rightFront.setEncPosition(distanceToEncTicks(meters));
		rightBack.setEncPosition(distanceToEncTicks(meters));
	}
	
	public double getLeftRPM() {
		return leftEncoder.getRate() * 60 / (2 * Math.PI * (wheelDiameter/2));
	}
	
	public double getRightRPM() {
		return rightEncoder.getRate() * 60 / (2 * Math.PI * (wheelDiameter/2));
	}
	
	public double getRightDistance() {
		return rightEncoder.getDistance();
	}
	
	public double getLeftDistance() {
		return leftEncoder.getDistance();
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
}