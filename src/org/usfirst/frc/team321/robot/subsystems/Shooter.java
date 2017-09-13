package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

	public CANTalon shootMotorLeft, shootMotorRight;
	
	public Shooter() {
		shootMotorLeft = new CANTalon(RobotMap.SHOOT_MOTOR_A);
		shootMotorRight = new CANTalon(RobotMap.SHOOT_MOTOR_B);
		
		shootMotorLeft.setVoltageRampRate(6);
		shootMotorRight.setVoltageRampRate(6);
	}
	
	public void setShooter(double power) {
		try {
			shootMotorLeft.set(RobotUtil.range(power, -1, 1));
			shootMotorRight.set(RobotUtil.range(power, -1, 1));
		} catch (Exception e) {
			shootMotorLeft.set(0);
			shootMotorRight.set(0);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}