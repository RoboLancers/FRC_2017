package org.usfirst.frc.team321.robot.utilities;

import org.usfirst.frc.team321.robot.OI;
import org.usfirst.frc.team321.robot.utilities.RobotUtil;

public class JoystickUtil {
	
	public final static double tolerance = 0.10;

	public static double getLeftYAxisValue(){
		if(Math.abs(OI.driveStick.getRawAxis(1)) > tolerance){
			return OI.driveStick.getRawAxis(1);
		}else{
			return 0;
		}
	}

	public static double getLeftYAxisNormalized(){
		return RobotUtil.squareAndKeepSign(getLeftYAxisValue());
	}
	
	public static double getRightYAxisValue(){
		if(Math.abs(OI.driveStick.getRawAxis(5)) > tolerance){
			return OI.driveStick.getRawAxis(5);
		}else{
			return 0;
		}
	}
	
	public static double getRightYAxisNormalized(){
		return RobotUtil.squareAndKeepSign(getRightYAxisValue());
	}
	
	public static double getLeftXAxisValue(){
		if(Math.abs(OI.driveStick.getRawAxis(0)) > tolerance){
			return OI.driveStick.getRawAxis(0);
		}else{
			return 0;
		}
	}
	
	public static double getLeftXAxisNormalized(){
		return RobotUtil.squareAndKeepSign(getLeftXAxisValue());
	}
	
	public static double getRightXAxisValue(){
		if(Math.abs(OI.driveStick.getRawAxis(3)) > tolerance){
			return OI.driveStick.getRawAxis(3);
		}else{
			return 0;
		}
	}
	
	public static double getRightXAxisNormalized(){
		return RobotUtil.squareAndKeepSign(getRightXAxisValue());
	}
	
	public static double getRudderYAxis() {
		return (OI.maniStick.getRawAxis(3) + 1)/4 + 0.5;
	}
}
