package org.usfirst.frc.team321.robot.utilities;

public class JoystickUtil {

	public static boolean isButtonPressed(Controller controller, int btnNumber) {
		return controller.buttons[btnNumber].get();
	}
}
