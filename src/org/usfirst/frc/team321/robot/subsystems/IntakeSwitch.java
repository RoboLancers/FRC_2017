package org.usfirst.frc.team321.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSwitch extends Subsystem {
	
	public DoubleSolenoid leftIntakeSwitch, rightIntakeSwitch;
	
	public IntakeSwitch(){
		leftIntakeSwitch = new DoubleSolenoid(8,9);
		rightIntakeSwitch = new DoubleSolenoid(0,1);
	}
	
	public void initDefaultCommand() {
		
		
	}
}
