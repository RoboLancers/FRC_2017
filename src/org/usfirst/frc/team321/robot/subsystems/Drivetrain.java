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
	
    public final double wheelDiameter = 0.19939;
    /**
     * Circumference of the wheel in meter
     */
    public final double wheelCircumference = wheelDiameter * Math.PI;

    /**
     * How many encoder ticks to go 1 meter
     */
    public final double ticksPerMeter = 360 / wheelCircumference;

    /**
     * Possible pulses per revolution (64, 128, 256) used for the encoder. The
     * gear ratio is also used for the distance the robot has traveled with the
     * pulses per revolution.
     */
    // private static final double pulsePerRev1 = 256, pulsePerRev2 = 128,
// pulsePerRev3 = 64;
	
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
		setRightPowers(power);
	}


	public void clearEncoder() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void stopMotors(){
		setAllPowers(0);
	}
	
    /**
     * Convert meter of robot movement to encoder movement in degrees
     * 
     * @param distance
     *            The distance in meter for encoder to turn
     * 
     * @return Returns the encoder movement in degrees
     */
    public double distanceToEncDegrees(double distanceInMeters) {
		// For future references
		// return ((distanceInMeters) * 360) / (wheelCircumference);
		return (360 / wheelCircumference) * distanceInMeters;
    }

    /**
     * Convert encoder rotation to meters
     * 
     * @param encRotation
     *            The amount of encoder rotation to convert to meters
     * 
     * @return
     */
    public double encDistanceToDistance(double encRotation) {
		// For future references
		// return (((encRotation / 360) * wheelCircumference));
		return ((encRotation / 360) * wheelCircumference) / 100;
    }

    /**
     * Gets the left motor speed as revolution per minutes
     * 
     * @return Returns the left RPM
     */
    public double getLeftSpeedInRPM() {
    	return (leftEncoder.getRate() / 360) * 60;
    }

    /**
     * Gets the right motor speed as revolution per minutes
     * 
     * @return Returns the right RPM
     */
    public double getRightSpeedInRPM() {
    	return (rightEncoder.getRate() / 360) * 60;
    }
    
    /**
     * Gets how much the left encoder has turned
     * 
     * @return Returns the distance the left encoder has traveled
     */
    public double getLeftEncoderDistance() {
    	return leftEncoder.getDistance();
    }

    /**
     * Gets how much the right encoder has turned
     * 
     * @return Returns the distance the right encoder has traveled
     */
    public double getRightEncoderDistance() {
    	return rightEncoder.getDistance();
    }
}


















