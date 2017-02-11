package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearShifter extends Subsystem {
	
	public static DoubleSolenoid gearShifter;
	
	public GearShifter(){
		gearShifter = new DoubleSolenoid(RobotMap.GEARSHIFTER_FORWARD,RobotMap.GEARSHIFTER_REVERSE);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
