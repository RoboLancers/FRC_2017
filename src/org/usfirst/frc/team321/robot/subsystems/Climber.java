package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.commands.ClimbSwitch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	public DoubleSolenoid leftClimberToggle, rightClimberToggle;
	
	public Climber(){
		leftClimberToggle = new DoubleSolenoid(4,5);
		rightClimberToggle = new DoubleSolenoid(6,7);
	}
	
	public void initDefaultCommand(){
	}
	
}
