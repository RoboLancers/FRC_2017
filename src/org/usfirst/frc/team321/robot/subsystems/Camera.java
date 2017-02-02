package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	
	public Camera(){	
	}
	
	public boolean gearTargetDetected(){
		try{
			Double.parseDouble(Robot.networkTable.getString("angletogeartarget", "Not Detected"));
		}catch(NumberFormatException num){
			return false;
		}
		return true;
	}
	
	public double gearTargetAngle(){
		return Double.parseDouble(Robot.networkTable.getString("angletogeartarget", "Not Detected"));
	}
	
	public boolean boilerTargetDetected(){
		try{
			Double.parseDouble(Robot.networkTable.getString("angletoboilertarget", "Not Detected"));
		}catch(NumberFormatException num){
			return false;
		}
		return true;
	}
	
	public double boilerTargetAngle(){
		return Double.parseDouble(Robot.networkTable.getString("angletoboilertarget", "Not Detected")); 
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
