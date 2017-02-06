package org.usfirst.frc.team321.robot.utilities;

public class RobotUtil {

	public static double range(double input, double min, double max){
		if(input > max){
			return max;
		}else if(input < min){
			return min;
		}else{
			return input;
		}
	}

	public static double floor(double num, int places) {
		double multiplier = 1;
		
		for(int x = 0; x < places; x++) {
			multiplier *= 10;
		}
		
		num *= multiplier;
		
		return (int)num / multiplier;
	}
	
	public static double squareAndKeepSign(double num){	
		if(num < 0){
			return -(num * num);
		}else{
			return num * num;
		}
	}
	
	/**
	 * Calculates the robot's motor speed to move to a target/angle
	 * 
	 * Note: Set power to 0 to move in place
	 */
	public static double[] moveToTarget(double power, double currentAngle, double targetAngle) {
		double[] motorSpeed = new double[2];
		
		motorSpeed[0] = RobotUtil.range(power - (currentAngle - targetAngle)/100, -1, 1);
	    motorSpeed[1] = RobotUtil.range(power + (currentAngle - targetAngle)/100, -1, 1);
	    
	    motorSpeed[0] = RobotUtil.floor(motorSpeed[0], 2);
	    motorSpeed[1] = RobotUtil.floor(motorSpeed[1], 2);
	    
	    return motorSpeed;
	}
}
