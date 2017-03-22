package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.UseDriveTrain;
import org.usfirst.frc.team321.robot.utilities.JoystickUtil;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *  Notes: Right Side is reversed
 */
public class Drivetrain extends Subsystem {
	
	public CANTalon leftFront, leftBack, rightFront, rightBack;
	public Encoder leftEncoder, rightEncoder;
	public static DriveMode driveMode = DriveMode.DRIVING;
	
    public final double wheelDiameter = 0.1016;
    public final double pulsePerRevolution = 256;
    public final double wheelCircumference = wheelDiameter * Math.PI;
    public final double meterPerPulse = wheelCircumference / pulsePerRevolution;
    
	public Drivetrain () {
		super("Drive Train");
		
		leftFront = new CANTalon(RobotMap.LEFT_FRONT_MOTOR);
		leftBack = new CANTalon(RobotMap.LEFT_BACK_MOTOR);
		
		rightFront = new CANTalon(RobotMap.RIGHT_FRONT_MOTOR);
		rightBack = new CANTalon(RobotMap.RIGHT_BACK_MOTOR);
		
		//rightBack.changeControlMode(TalonControlMode.Follower);
		//rightBack.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//rightBack.set(rightFront.getDeviceID());
		
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B);
		
		leftEncoder.setDistancePerPulse(meterPerPulse);
		rightEncoder.setDistancePerPulse(meterPerPulse);
		
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new UseDriveTrain());
	}
	
	public static enum DriveMode {
		DRIVING, AUTO_ADJUST, CLIMBING;
	} 
	
	public void setLeftPowers(double power){
		power = power * 0.90;
		
		leftFront.set(RobotUtil.range(power, -1, 1));
		leftBack.set(RobotUtil.range(power, -1, 1));
	}
	
	public void setRightPowers(double power){
		power = power * -0.90;
		
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