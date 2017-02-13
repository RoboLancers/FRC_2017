package org.usfirst.frc.team321.robot;

import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.commands.GroupConveyerIndexer;
import org.usfirst.frc.team321.robot.commands.SwitchDriveMode;
import org.usfirst.frc.team321.robot.commands.UseConveyor;
import org.usfirst.frc.team321.robot.commands.UseShooter;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain.DriveMode;
import org.usfirst.frc.team321.robot.subsystems.GearHolder;
import org.usfirst.frc.team321.robot.subsystems.GearShifter;
import org.usfirst.frc.team321.robot.subsystems.IntakeFlap;

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
		
		driveBtn[6].whileHeld(new SwitchDriveMode(DriveMode.CLIMBING));
		maniBtn[5].whileHeld(new SwitchDriveMode(DriveMode.AUTO_ADJUST));
		//driveBtn[6].whenPressed(new DSolenoidToggle(Robot.climber, Climber.climberToggle));
		driveBtn[1].whenPressed(new DSolenoidToggle(Robot.gearshifter, GearShifter.gearShifter));
		maniBtn[7].whenPressed(new DSolenoidToggle(Robot.gearholder, GearHolder.gearEjector));
		maniBtn[8].whenPressed(new DSolenoidToggle(Robot.intakeflap, IntakeFlap.intakeflap));
		
		maniBtn[11].whileHeld(new UseConveyor(-1));
		maniBtn[1].whileHeld(new UseShooter(0.7));
		maniBtn[2].whileHeld(new GroupConveyerIndexer());
		//maniBtn[12].whenPressed(new StartSSHCamera());
	}
}
