package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSwitch extends Subsystem {
	
	public static DoubleSolenoid intakeSwitch;
	
	public IntakeSwitch(){
		intakeSwitch = new DoubleSolenoid(RobotMap.INTAKE_FORWARD,RobotMap.INTAKE_REVERSE);
	}
	
	@Override
	public void initDefaultCommand() {
		
	}
}
