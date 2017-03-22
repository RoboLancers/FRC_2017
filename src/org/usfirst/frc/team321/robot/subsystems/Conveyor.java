package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Conveyor extends Subsystem{

	public CANTalon intakeMotor;
	
	public Conveyor(){
		intakeMotor = new CANTalon(RobotMap.CONVEYOR_MOTOR);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
