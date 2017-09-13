package org.usfirst.frc.team321.robot;

import org.usfirst.frc.team321.robot.autonomous.AutoCrossLine;
import org.usfirst.frc.team321.robot.autonomous.AutoMoveRobot;
import org.usfirst.frc.team321.robot.autonomous.AutoPlantGear;
import org.usfirst.frc.team321.robot.autonomous.AutoSideGear;
import org.usfirst.frc.team321.robot.autonomous.AutoStandStill;
import org.usfirst.frc.team321.robot.commands.DSolenoidToggle;
import org.usfirst.frc.team321.robot.subsystems.Camera;
import org.usfirst.frc.team321.robot.subsystems.Climber;
import org.usfirst.frc.team321.robot.subsystems.Conveyor;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.subsystems.GearHolder;
import org.usfirst.frc.team321.robot.subsystems.GearShifter;
import org.usfirst.frc.team321.robot.subsystems.Indexer;
import org.usfirst.frc.team321.robot.subsystems.IntakeFlap;
import org.usfirst.frc.team321.robot.subsystems.Pneumatics;
import org.usfirst.frc.team321.robot.subsystems.Sensors;
import org.usfirst.frc.team321.robot.subsystems.Shooter;
import org.usfirst.frc.team321.robot.utilities.JoystickUtil;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static Climber climber;
	public static Shooter shooter;
	public static Conveyor conveyor;
	public static Indexer indexer;
	
	public static Pneumatics pneumatics;
	public static GearHolder gearholder;
	public static IntakeFlap intakeflap;
	public static GearShifter gearshifter;
	
	public static Sensors sensors;
	public static Camera camera;

	public static OI oi;
	
	public static NetworkTable networkTable;
	public static double angleOffset;
	
	SendableChooser chooser = new SendableChooser();
	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void robotInit() {
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		climber = new Climber();
		indexer = new Indexer();
		conveyor = new Conveyor();

		pneumatics = new Pneumatics();
		gearholder = new GearHolder();
		intakeflap = new IntakeFlap();
		gearshifter = new GearShifter();
		
		sensors = new Sensors();
		camera = new Camera();
		
		oi = new OI();
		
		chooser = new SendableChooser();
		
		SmartDashboard.putData("Auto mode", chooser);
		
		
		chooser.addDefault("Stand Still", new AutoStandStill());
		chooser.addObject("Move Forward Test", new AutoMoveRobot());
		chooser.addObject("Cross the Line", new AutoCrossLine());
		chooser.addObject("Place Gear", new AutoPlantGear());
		chooser.addObject("Side Gear (Left)", new AutoSideGear(true));
		chooser.addObject("Side Gear (Right)", new AutoSideGear(false));
		
		networkTable = NetworkTable.getTable("jetson");
		networkTable.putString("Angle To Gear", "Not Detected");
		
		SmartDashboard.putString("Angle To Gear", "Not Detected");
	}

	/**
	 * Programmer created method that displays robot data
	 */
	public void displayRobotData() {
		try {
			SmartDashboard.putString("Angle To Gear", networkTable.getString("Angle To Gear", "Not Detected"));
		} catch (Exception e) { }
		
		try {
			SmartDashboard.putNumber("Robot Angle", sensors.getRobotAngle());

			SmartDashboard.putNumber("Displacement", sensors.getRobotDisplacement());
		
			SmartDashboard.putNumber("Shooter Speed", OI.flightStick.getRudderAxis());
		} catch (Exception e) { }
		
		try {
			SmartDashboard.putString("Drive Mode", Drivetrain.driveMode.toString());
		} catch (Exception e) { }
		
	}
	
	@Override
	public void disabledInit() {
		new DSolenoidToggle(Robot.climber, Climber.climberToggle, DoubleSolenoid.Value.kForward);
		new DSolenoidToggle(Robot.gearshifter, GearShifter.gearShifter, DoubleSolenoid.Value.kReverse);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		sensors.resetNavX();
		
		autonomousCommand = (Command) chooser.getSelected();
		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		displayRobotData();
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		sensors.resetNavX();
		
		Robot.gearholder.closeDoor();
		
		autonomousCommand = (Command) chooser.getSelected();
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		
		displayRobotData();
		
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}