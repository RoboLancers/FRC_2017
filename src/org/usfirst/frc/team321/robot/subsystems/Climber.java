package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	public static DoubleSolenoid climberToggle;
	
	public Climber(){
		climberToggle = new DoubleSolenoid(RobotMap.CLIMBER_FORWARD,RobotMap.CLIMBER_REVERSE);
		climberToggle.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void initDefaultCommand(){
		
	}
}
