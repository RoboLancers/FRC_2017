package org.usfirst.frc.team321.robot.utilities;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public abstract class Controller {

	public Joystick joystick;
	public JoystickButton[] buttons;
	
	public void configureBtn() {
		buttons = new JoystickButton[13];
		
		for(int i = 1; i < buttons.length; i++){ 
			buttons[i] = new JoystickButton(joystick, i);	
		}
	}
}
