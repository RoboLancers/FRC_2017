package org.usfirst.frc.team321.robot;

import org.usfirst.frc.team321.robot.commands.DSolenoidHold;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.commands.GroupConveyerIndexer;
import org.usfirst.frc.team321.robot.commands.GroupFlapShooter;
import org.usfirst.frc.team321.robot.commands.SwitchDriveMode;
import org.usfirst.frc.team321.robot.commands.UseConveyor;
import org.usfirst.frc.team321.robot.commands.UseShooter;
import org.usfirst.frc.team321.robot.subsystems.Climber;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain.DriveMode;
import org.usfirst.frc.team321.robot.subsystems.GearHolder;
import org.usfirst.frc.team321.robot.subsystems.GearShifter;
import org.usfirst.frc.team321.robot.subsystems.IntakeFlap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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
		
		//Drive Modes
		maniBtn[5].whileHeld(new SwitchDriveMode(DriveMode.AUTO_ADJUST));
		
		//Pneumatics
		driveBtn[8].whenPressed(new DSolenoidToggle(Robot.climber, Climber.climberToggle));
		driveBtn[1].whenPressed(new DSolenoidToggle(Robot.gearshifter, GearShifter.gearShifter));
		maniBtn[9].whileHeld(new DSolenoidHold(Robot.gearholder, GearHolder.gearEjector, DoubleSolenoid.Value.kForward));
		maniBtn[10].whileHeld(new DSolenoidHold(Robot.intakeflap, IntakeFlap.intakeflap, DoubleSolenoid.Value.kForward));

		//Mechanisms
		maniBtn[7].whileHeld(new UseConveyor(-1));
		maniBtn[1].whileHeld(new GroupFlapShooter());
		maniBtn[2].whileHeld(new GroupConveyerIndexer(0.7));
		
		//debug buttons
		maniBtn[11].whenPressed(new DSolenoidToggle(Robot.gearholder, GearHolder.gearEjector));
		maniBtn[12].whenPressed(new DSolenoidToggle(Robot.intakeflap, IntakeFlap.intakeflap));
		maniBtn[4].whileHeld(new UseShooter());
	}
}