
package org.usfirst.frc.team321.robot;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.usfirst.frc.team321.autonomous.MoveForwardTime;
import org.usfirst.frc.team321.autonomous.MoveStraightWithEncoder;
import org.usfirst.frc.team321.robot.subsystems.Climber;
import org.usfirst.frc.team321.robot.subsystems.Drivetrain;
import org.usfirst.frc.team321.robot.subsystems.Intake;
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

	public static Pneumatics pneumatics;
	public static Drivetrain drivetrain;
	public static OI oi;
	public static Climber climber;
	public static Shooter shooter;
	public static Intake intake;
	public static Sensors sensors;
	
	public static NetworkTable networkTable;
	//BufferedImage image;
	//InputStream inputStream;
	
	public static double angleOffset;
	//public static byte[] imageByte = new byte[3];
	Command autonomousCommand;
	SendableChooser chooser = new SendableChooser();

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
		intake = new Intake();
		sensors = new Sensors();
		oi = new OI();
		
		chooser = new SendableChooser();
		
		SmartDashboard.putData("Auto mode", chooser);
		
		//chooser.addDefault("No Autonomous Code", null);
		//chooser.addObject("Move Straight Forward", new MoveStraightWithEncoder());
		
		//networkTable = NetworkTable.getTable("jetson");
		//networkTable.putNumber("Turn Angle", 0);
		//networkTable.putRaw("Image", imageByte);
		
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
			//angleOffset = networkTable.getNumber("angletogoal", 0);
			//SmartDashboard.putNumber("Angle to target", angleOffset);
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		sensors.resetNavX();
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
//		angleOffset = networkTable.getNumber("angletogoal", 0);
//		SmartDashboard.putNumber("Angle to target", angleOffset);
//		
//		imageByte = networkTable.getRaw("Image", imageByte);
//		inputStream = new ByteArrayInputStream(imageByte);
//		try {
//			image = ImageIO.read(inputStream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		SmartDashboard.putNumber("Left Motor Speed", sensors.moveInHeading(0, 90)[0]);
		SmartDashboard.putNumber("Right Motor Speed", sensors.moveInHeading(0, 90)[1]);
		SmartDashboard.putNumber("Angle", sensors.navX.getAngle());

		Scheduler.getInstance().run();
	}

	/**10.7
	 * 25.5
	 * 
	 * 
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
	
}
