package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Encoder;

public class Climber {
	
	public CANTalon climbMotor;
	public Encoder climbEncoder;
	public Climber(){
		
		climbMotor = new CANTalon(RobotMap.CLIMB_MOTOR);
	}
	
	public void setPower(int power){
		climbMotor.set(power);
	}
	
	public void initDefaultCommand(){
		//setDefaultCommand(CLIMBER_COMMAND);
	}
	
}
