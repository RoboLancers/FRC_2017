package org.usfirst.frc.team321.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem {

	public AHRS navX;
	
	public Sensors() {
		navX = new AHRS(SerialPort.Port.kMXP);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
