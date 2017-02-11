package org.usfirst.frc.team321.robot;

import org.usfirst.frc.team321.robot.commands.ConveyBall;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.commands.GearEjector;
import org.usfirst.frc.team321.robot.commands.GroupConveyorShooter;
import org.usfirst.frc.team321.robot.commands.OpenFlap;
import org.usfirst.frc.team321.robot.commands.SwitchGear;
import org.usfirst.frc.team321.robot.commands.UseIndexer;
import org.usfirst.frc.team321.robot.subsystems.Climber;
import org.usfirst.frc.team321.robot.subsystems.IntakeSwitch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
		
		for(int i = 1; i < maniBtn.length; i++){
			maniBtn[i] = new JoystickButton(maniStick, i);
		}
		
		driveBtn[1].whenPressed(new DSolenoidToggle(Robot.climber, Climber.climberToggle));
		driveBtn[5].whenPressed(new DSolenoidToggle(Robot.intakeswitch, IntakeSwitch.intakeSwitch));
		maniBtn[7].whenPressed(new GearEjector());
		maniBtn[8].whenPressed(new OpenFlap());
		
		maniBtn[11].whileHeld(new ConveyBall(-1));
		maniBtn[1].whileHeld(new UseIndexer());
		maniBtn[2].whileHeld(new GroupConveyorShooter());
	}
}
