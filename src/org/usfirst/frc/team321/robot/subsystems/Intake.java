package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{

	public CANTalon intakeMotor;
	
	public Intake(){
		intakeMotor = new CANTalon(RobotMap.SHOOT_MOTOR);
	}
	
	public enum IntakeValues{
		INTAKE(1.0), STOP(0), OUTTAKE(-1.0);
		
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
		// TODO Auto-generated method stub
		
	}

}
