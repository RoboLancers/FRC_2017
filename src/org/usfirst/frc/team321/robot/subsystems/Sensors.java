package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem {

	public AHRS navX;
	public DigitalInput touch;
	public DigitalInput irSensor;
	
	private double startDisplacement;
	public boolean isTracking;
	
	public Sensors() {
		navX = new AHRS(SerialPort.Port.kMXP);
		touch = new DigitalInput(RobotMap.BUMPER_TOUCH);
		irSensor = new DigitalInput(RobotMap.IR_SENSOR);
		
		navX.reset();
		navX.resetDisplacement();

		isTracking = false;
		startDisplacement = getRobotDisplacement();
	}
	
	public boolean isGearLoaded() {
		return irSensor.get();
	}
	
	public boolean isGearPenetrated(){
		return !touch.get();
	}
	
	public double[] moveInHeading(double power, double degrees) {
		return RobotUtil.moveToTarget(power, navX.getAngle(), degrees);
	}
	
	public double getRobotAngle() {
		return RobotUtil.floor(navX.getAngle(), 2);
	}
	
	public double getRobotHeading() {
		if (navX.getAngle() < 0) {
			return RobotUtil.floor(360 - Math.abs(navX.getAngle() % 360), 2);
		} else {
			return RobotUtil.floor(navX.getAngle() % 360, 2);
		}
	}
	
	public double getRobotVelocity() {
		return RobotUtil.floor(Math.hypot(navX.getVelocityX(), navX.getVelocityY()), 2);
	}
	
	public double getRobotDisplacement() {
		return RobotUtil.floor(Math.hypot(navX.getDisplacementX(), navX.getDisplacementY()), 2);
	}
	
	public void resetNavX() {
		navX.reset();
		navX.resetDisplacement();
	}
	
	public void startTracking() {
		navX.resetDisplacement();
		isTracking = true;
		startDisplacement = getRobotDisplacement();
	}
	
	public boolean hasDroveDistance(double meters) {
		boolean hasDroven = Math.abs(getRobotDisplacement() - startDisplacement) > meters;
		if (isTracking && hasDroven) {
			isTracking = false;
			return hasDroven;
		}
		return false;
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
