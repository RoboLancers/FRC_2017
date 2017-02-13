package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	
	UsbCamera camera;
	MjpegServer mjpegServer;
	CvSink cvSink;
	CvSource output;
	
	public Camera(){
		camera = new UsbCamera("Driver Camera 0", 0);
		mjpegServer = new MjpegServer("Driver Server 0", 1180);
		mjpegServer.setSource(camera);
		cvSink = new CvSink("CV Driver Camera 0");
		cvSink.setSource(camera);
		output = new CvSource("Driver Cam", PixelFormat.kMJPEG, 1280, 720, 30);
	}
	
	public boolean gearTargetDetected(){
		try{
			Double.parseDouble(Robot.networkTable.getString("Angle To Gear", "Not Detected"));
		}catch(NumberFormatException num){
			return false;
		}
		return true;
	}
	
	public double gearTargetAngle(){
		return Double.parseDouble(Robot.networkTable.getString("Angle To Gear", "Not Detected"));
	}
	
	public boolean boilerTargetDetected(){
		try{
			Double.parseDouble(Robot.networkTable.getString("Angle To Boiler", "Not Detected"));
		}catch(NumberFormatException num){
			return false;
		}
		return true;
	}
	
	public double boilerTargetAngle(){
		return Double.parseDouble(Robot.networkTable.getString("Angle To Boiler", "Not Detected")); 
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}