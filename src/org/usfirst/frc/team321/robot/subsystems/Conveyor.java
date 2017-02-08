package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.ConveyBall;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Conveyor extends Subsystem{

	public CANTalon intakeMotor;
	
	public Conveyor(){
		intakeMotor = new CANTalon(RobotMap.CONVEYOR_MOTOR);
	}
	
	public enum IntakeValues{
		INTAKE(1.0), STOP(0.0), OUTTAKE(-1.0);
		
		private double value;
		
		private IntakeValues(double value){
			this.value = value;
		}
		
		public double getValue(){
			return value;
		}
	}
	@Override
	protected void initDefaultCommand() {
	}

}