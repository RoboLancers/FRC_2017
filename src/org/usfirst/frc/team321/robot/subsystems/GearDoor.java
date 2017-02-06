package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.commands.GearEjector;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearDoor extends Subsystem {

	public DoubleSolenoid leftGearEjector, rightGearEjector;
	
	public GearDoor() {
		leftGearEjector = new DoubleSolenoid(2,3);
		rightGearEjector = new DoubleSolenoid(0,1);
	}
	
	public void initDefaultCommand(){
	}
	
}
