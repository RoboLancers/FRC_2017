package org.usfirst.frc.team321.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XBoxController extends Controller {
	
	private  double tolerance = 0.10;
	
	//XBox Controller numbers
	public static int LEFT_X_ID = 0;
	public static int LEFT_Y_ID = 1;
	public static int RIGHT_X_ID = 2;
	public static int RIGHT_Y_ID = 3;
	public static int A_ID = 2;
	public static int B_ID = 3;
	public static int X_ID = 1;
	public static int Y_ID = 4;
	public static int LT_ID = 5;
	public static int RT_ID = 6;
	public static int LB_ID = 7;
	public static int RB_ID = 8;
	public static int SELECT_ID = 9;
	public static int START_ID = 10;
	public static int LEFT_JOY_BTN_ID = 11;
	public static int RIGHT_JOY_BTN_ID = 12;
	
	public XBoxController(int portNumber) {
		this.joystick = new Joystick(portNumber);
		this.configureBtn();
	}

	public JoystickButton A() {
		return this.buttons[A_ID];
	}
	
	public JoystickButton B() {
		return this.buttons[B_ID];
	}
	
	public JoystickButton X() {
		return this.buttons[X_ID];
	}
	
	public JoystickButton Y() {
		return this.buttons[Y_ID];
	}
	
	public JoystickButton LT() {
		return this.buttons[LT_ID];
	}
	
	public JoystickButton RT() {
		return this.buttons[RT_ID];
	}
	
	public JoystickButton LB() {
		return this.buttons[LB_ID];
	}
	
	public JoystickButton RB() {
		return this.buttons[RB_ID];
	}
	
	public JoystickButton select() {
		return this.buttons[SELECT_ID];
	}
	
	public JoystickButton start() {
		return this.buttons[START_ID];
	}
	
	public JoystickButton leftJoyBtn() {
		return this.buttons[LEFT_JOY_BTN_ID];
	}
	
	public JoystickButton rightJoyBtn() {
		return this.buttons[RIGHT_JOY_BTN_ID];
	}
	
	public double getLeftYAxisValue(){
		if(Math.abs(this.joystick.getRawAxis(LEFT_Y_ID)) > tolerance){
			return this.joystick.getRawAxis(LEFT_Y_ID);
		}else{
			return 0;
		}
	}
	
	public double getRawLeftYAxisValue() {
		return this.joystick.getRawAxis(LEFT_Y_ID);
	}

	public double getLeftYAxisNormalized(){
		return RobotUtil.squareKeepSign(getLeftYAxisValue());
	}
	
	public double getRightYAxisValue(){
		if(Math.abs(this.joystick.getRawAxis(RIGHT_Y_ID)) > tolerance){
			return this.joystick.getRawAxis(RIGHT_Y_ID);
		}else{
			return 0;
		}
	}
	
	public double getRawRightYAxisValue() {
		return this.joystick.getRawAxis(RIGHT_Y_ID);
	}
	
	public double getRightYAxisNormalized(){
		return RobotUtil.squareKeepSign(getRightYAxisValue());
	}
	
	public double getLeftXAxisValue(){
		if(Math.abs(this.joystick.getRawAxis(LEFT_X_ID)) > tolerance){
			return this.joystick.getRawAxis(LEFT_X_ID);
		}else{
			return 0;
		}
	}
	
	public double getRawLeftXAxisValue() {
		return this.joystick.getRawAxis(LEFT_X_ID);
	}
	
	public double getLeftXAxisNormalized(){
		return RobotUtil.squareKeepSign(getLeftXAxisValue());
	}
	
	public double getRightXAxisValue(){
		if(Math.abs(this.joystick.getRawAxis(RIGHT_X_ID)) > tolerance){
			return this.joystick.getRawAxis(RIGHT_X_ID);
		}else{
			return 0;
		}
	}
	
	public double getRawRightXAxisValue(){
		return this.joystick.getRawAxis(RIGHT_X_ID);
	}
	
	public double getRightXAxisNormalized(){
		return RobotUtil.squareKeepSign(getRightXAxisValue());
	}
}
