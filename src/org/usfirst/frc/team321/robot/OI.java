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
import org.usfirst.frc.team321.robot.utilities.FlightStick;
import org.usfirst.frc.team321.robot.utilities.XBoxController;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static XBoxController xboxController = new XBoxController(0);
	public static FlightStick flightStick = new FlightStick(1);
	
	public OI(){
		
		//Drive Modes
		flightStick.topLeft().whileHeld(new SwitchDriveMode(DriveMode.AUTO_ADJUST));
		//driveBtn[JoystickUtil.LT].whileHeld(new SwitchDriveMode(DriveMode.CLIMBING));
		
		//Pneumatics
		xboxController.RT().whileHeld(new DSolenoidHold(Robot.gearshifter, GearShifter.gearShifter, DoubleSolenoid.Value.kForward));
		flightStick.farMiddle().whileHeld(new DSolenoidHold(Robot.gearholder, GearHolder.gearEjector, DoubleSolenoid.Value.kForward));
		flightStick.innerMiddle().whileHeld(new DSolenoidHold(Robot.intakeflap, IntakeFlap.intakeflap, DoubleSolenoid.Value.kForward));

		//Mechanisms
		flightStick.trigger().whileHeld(new GroupFlapShooter());
		flightStick.shooter().whileHeld(new GroupConveyerIndexer(0.7));
		
		//debug buttons
		flightStick.farTop().whileHeld(new UseConveyor(-1));
		flightStick.farBottom().whenPressed(new DSolenoidToggle(Robot.gearholder, GearHolder.gearEjector));
		flightStick.innerMiddle().whenPressed(new DSolenoidToggle(Robot.intakeflap, IntakeFlap.intakeflap));
		flightStick.bottomRight().whileHeld(new UseShooter());
		xboxController.RB().whenPressed(new DSolenoidToggle(Robot.climber, Climber.climberToggle));
		
	}
}