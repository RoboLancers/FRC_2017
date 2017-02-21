
package org.usfirst.frc.team321.robot;

import org.usfirst.frc.team321.robot.autonomous.AutoCrossLine;
import org.usfirst.frc.team321.robot.autonomous.AutoGearAndCrossLine;
import org.usfirst.frc.team321.robot.autonomous.AutoMoveRobot;
import org.usfirst.frc.team321.robot.autonomous.AutoStandStill;
import org.usfirst.frc.team321.robot.autonomous.AutoTurnTowardsTarget;
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
		chooser.addObject("Turn to Target", new AutoTurnTowardsTarget());
		chooser.addObject("Move Forward Test", new AutoMoveRobot());
		chooser.addObject("Put Gear and Cross Line", new AutoGearAndCrossLine());
		chooser.addObject("Cross the Line", new AutoCrossLine());
		
		networkTable = NetworkTable.getTable("jetson");
		networkTable.putString("Angle To Gear", "Not Detected");
		networkTable.putString("Angle To Boiler", "Not Detected");
		
		SmartDashboard.putString("Angle To Gear", "Not Detected");
		SmartDashboard.putString("Angle To Boiler", "Not Detected");
	}

	/**
	 * Programmer created method that displays robot data
	 */
	public void displayRobotData() {
		if(camera.gearTargetDetected()){
			SmartDashboard.putString("Angle To Gear", networkTable.getString("Angle To Gear", "Not Detected"));
		}
		
		if(camera.boilerTargetDetected()){
			SmartDashboard.putString("Angle To Boiler", networkTable.getString("Angle To Boiler", "Not Detected"));
		}
	
		SmartDashboard.putNumber("Robot Angle", sensors.getRobotAngle());
		SmartDashboard.putNumber("Robot Heading", sensors.getRobotHeading());
		SmartDashboard.putNumber("Robot Velocity", sensors.getRobotVelocity());
		SmartDashboard.putNumber("Displacement", sensors.getRobotDisplacement());
		SmartDashboard.putBoolean("Autonomous Running", autonomousCommand.isRunning());
		
		SmartDashboard.putString("Gear Holder", GearHolder.gearEjector.get() == DoubleSolenoid.Value.kForward ? "Held" : "Released");
		SmartDashboard.putString("Gear Shifer", GearShifter.gearShifter.get() == DoubleSolenoid.Value.kForward ? "Slow" : "Fast" );
		SmartDashboard.putString("Intake Flap", IntakeFlap.intakeflap.get() == DoubleSolenoid.Value.kForward ? "Gear Intake" : "Ball Intake");
		SmartDashboard.putString("Climber", Climber.climberToggle.get() == DoubleSolenoid.Value.kForward ? "Climber Engaged" : "Driving");
	
		SmartDashboard.putNumber("Shooter Speed", -JoystickUtil.getRudderYAxis());
		
		SmartDashboard.putString("Drive Mode", Drivetrain.driveMode.toString());
		
		SmartDashboard.putBoolean("Gear Loaded", sensors.isGearLoaded());
		SmartDashboard.putBoolean("Touch Pad", sensors.isGearPenetrated());
		
		SmartDashboard.putData("Shooter Running", shooter);
		SmartDashboard.putData("Climb Switch Running", climber);
		SmartDashboard.putData("Conveyor Running", conveyor);
		SmartDashboard.putData("Drive Train Running", drivetrain);
		SmartDashboard.putData("Pneumatics Running", pneumatics);
		SmartDashboard.putData("Gear Door Running", gearholder);
		SmartDashboard.putData("Gear Shifter Running", gearshifter);
		SmartDashboard.putData("Intake Flap Running", intakeflap);
		SmartDashboard.putData("Indexer Running", indexer);
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		new DSolenoidToggle(Robot.climber, Climber.climberToggle, DoubleSolenoid.Value.kReverse);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		sensors.resetNavX();
		
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		displayRobotData();
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Robot.gearholder.closeDoor();
		
		autonomousCommand = (Command) chooser.getSelected();
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		displayRobotData();
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}