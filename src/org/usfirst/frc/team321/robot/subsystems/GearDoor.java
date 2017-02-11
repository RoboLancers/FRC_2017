package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearDoor extends Subsystem {

	public static DoubleSolenoid gearEjector;
	
	public GearDoor() {
		gearEjector = new DoubleSolenoid(RobotMap.GEARDOOR_FORWARD,RobotMap.GEARDOOR_REVERSE);
		gearEjector.set(DoubleSolenoid.Value.kForward);
	}
	
	public void initDefaultCommand(){
	}
	
}
