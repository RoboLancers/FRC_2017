package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Indexer extends Subsystem {

	public CANTalon indexer;

	public Indexer() {
		indexer = new CANTalon(RobotMap.INDEXER_MOTOR);	
	}
	
	public void setIndexer(double power) {
		indexer.set(RobotUtil.range(power, -1, 1));
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
