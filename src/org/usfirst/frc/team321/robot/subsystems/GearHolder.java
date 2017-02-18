package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.EjectGearWithSensor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearHolder extends Subsystem {

	public static DoubleSolenoid gearEjector;
	
	public GearHolder() {
		gearEjector = new DoubleSolenoid(RobotMap.GEARDOOR_FORWARD,RobotMap.GEARDOOR_REVERSE);
		gearEjector.set(DoubleSolenoid.Value.kForward);
	}
	
	public void openDoor() {
		gearEjector.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeDoor() {
		gearEjector.set(DoubleSolenoid.Value.kForward);
	}
	
	public void initDefaultCommand(){
		
	}
	
}
