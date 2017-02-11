
package org.usfirst.frc.team321.robot;

import org.usfirst.frc.team321.autonomous.AutoMoveWithEncoder;
import org.usfirst.frc.team321.autonomous.AutoStandStill;
import org.usfirst.frc.team321.autonomous.AutoTurnTowardsTarget;
import org.usfirst.frc.team321.robot.subsystems.Camera;
import org.usfirst.frc.team321.robot.subsystems.Climber;
import org.usfirst.frc.team321.robot.subsystems.Conveyor;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.subsystems.GearDoor;
import org.usfirst.frc.team321.robot.subsystems.GearShifter;
import org.usfirst.frc.team321.robot.subsystems.Indexer;
import org.usfirst.frc.team321.robot.subsystems.IntakeSwitch;
import org.usfirst.frc.team321.robot.subsystems.Pneumatics;
import org.usfirst.frc.team321.robot.subsystems.Sensors;
import org.usfirst.frc.team321.robot.subsystems.Shooter;

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
	public static GearDoor geardoor;
	public static IntakeSwitch intakeswitch;
	public static GearShifter gearshift;
	
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
	@Override
	public void robotInit() {
		pneumatics = new Pneumatics();
		drivetrain = new Drivetrain();
		shooter = new Shooter();
		climber = new Climber();
		indexer = new Indexer();
		conveyor = new Conveyor();
		geardoor = new GearDoor();
		intakeswitch = new IntakeSwitch();
		gearshift = new GearShifter();
		sensors = new Sensors();
		camera = new Camera();
		
		oi = new OI();
		
		chooser = new SendableChooser();
		
		SmartDashboard.putData("Auto mode", chooser);
		
		chooser.addDefault("Stand Still", new AutoStandStill());
		chooser.addObject("Move Straight Forward", new AutoMoveWithEncoder());
		chooser.addObject("Turn to Target", new AutoTurnTowardsTarget());
		
		networkTable = NetworkTable.getTable("jetson");
		networkTable.putString("Angle to Gear", "Not Detected");
		networkTable.putString("Angle to Boiler", "Not Detected");
	}

	/**
	 * Programmer created method that displays robot data
	 */
	public void displayRobotData() {
		if(camera.gearTargetDetected()){
			SmartDashboard.putString("Angle to Gear", networkTable.getString("Angle to Gear", "Not Detected"));
		}
		
		if(camera.boilerTargetDetected()){
			SmartDashboard.putString("Angle to Boiler", networkTable.getString("Angle to Boiler", "Not Detected"));
		}
		
		SmartDashboard.putNumber("Left Motor Speed", sensors.moveInHeading(0, 90)[0]);
		SmartDashboard.putNumber("Right Motor Speed", sensors.moveInHeading(0, 90)[1]);
		SmartDashboard.putNumber("Angle", sensors.getRobotAngle());
		SmartDashboard.putNumber("Heading", sensors.getRobotHeading());
		SmartDashboard.putNumber("Robot Velocity", sensors.getRobotVelocity());
		SmartDashboard.putBoolean("Autonomous Running", autonomousCommand.isRunning());
		
		SmartDashboard.putData("Shooter Running", shooter);
		SmartDashboard.putData("Climb Switch Running", climber);
		SmartDashboard.putData("Conveyor Running", conveyor);
		SmartDashboard.putData("Drive Train Running", drivetrain);
		SmartDashboard.putData("Pneumatics Running", pneumatics);
		SmartDashboard.putData("Gear Door Running", geardoor);
	}
	
	public void putRobotLabels() {
		SmartDashboard.putString("Auto Command", autonomousCommand.toString());
		SmartDashboard.putString("LeftMotorSpeed", "Left Motor Speed");
		SmartDashboard.putString("RightMotorSpeed", "Right Motor Speed");
		SmartDashboard.putString("RobotHeading", "Robot Heading");
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

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
		putRobotLabels();
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		sensors.resetNavX();
		
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
		putRobotLabels();
		
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
