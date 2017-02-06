package org.usfirst.frc.team321.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team321.robot.commands.BallShoot;
import org.usfirst.frc.team321.robot.commands.ClimbSwitch;
import org.usfirst.frc.team321.robot.commands.GearEjector;
import org.usfirst.frc.team321.robot.commands.ConveyBall;
import org.usfirst.frc.team321.robot.subsystems.Conveyor.IntakeValues;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static Joystick driveStick, maniStick;
	public static JoystickButton[] driveBtn, maniBtn;
	
	public OI(){
		driveStick = new Joystick(0);
		maniStick = new Joystick(1);
		
		driveBtn = new JoystickButton[13];
		maniBtn = new JoystickButton[13];
		
		for(int i = 1; i < driveBtn.length; i++){
			driveBtn[i] = new JoystickButton(driveStick, i);
			
		}
		
			for(int i = 1; i <maniBtn.length; i++){
			maniBtn[i] = new JoystickButton(maniStick, i);
		}
		
		driveBtn[1].whenPressed(new ClimbSwitch());
		maniBtn[5].whenPressed(new GearEjector());
		
		maniBtn[2].whileHeld(new BallShoot());
		maniBtn[3].whileHeld(new ConveyBall());
	}
}
