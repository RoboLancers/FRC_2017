package org.usfirst.frc.team321.robot.subsystems;

import java.util.Arrays;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sensors extends Subsystem {

	public AHRS navX;
	public SerialPort ultrasonic;
	
	private String[] ultrasonicBuffer = new String[20];
	private double[] ultrasonicMedian = new double[5];
	
	public Sensors() {
		navX = new AHRS(SerialPort.Port.kMXP);
		ultrasonic = new SerialPort(9600, SerialPort.Port.kOnboard, 8, SerialPort.Parity.kNone, SerialPort.StopBits.kOne);
		
		for(int x = 0; x < ultrasonicBuffer.length - 1; x++) {
			ultrasonicBuffer[x] = "";
		}
		
		for(int x = 0; x < ultrasonicMedian.length - 1; x++) {
			ultrasonicMedian[x] = 0;
		}
	}
	
	public String getRawUltrasonicReading() {
		ultrasonicBuffer[ultrasonicBuffer.length - 1] = ultrasonic.readString();
		
		for(int x = 0; x < ultrasonicBuffer.length - 1; x++) {
			ultrasonicBuffer[x] = ultrasonicBuffer[x+1];
		}
		
		for(int x = 0; x < ultrasonicBuffer.length - 1; x++) {
			if (ultrasonicBuffer[x].startsWith("R")) {
				return ultrasonicBuffer[x];
			}
		}
		
		return "No Detection";
	}
	
	public double getMedianUltrasonicInMeters() {
		for(int x = 0; x < ultrasonicMedian.length - 1; x++) {
			ultrasonicMedian[x] = ultrasonicMedian[x+1];
		}
		
		ultrasonicMedian[ultrasonicMedian.length - 1] = getRawUltrasonicInMeters();
		
		double[] median = new double[ultrasonicMedian.length];
		
		System.arraycopy(ultrasonicMedian, 0, median, 0, 5);
		
		Arrays.sort(median);
		
		return median[(median.length-1) / 2];
	}
	
	public double getRawUltrasonicInMeters() {
		try {
			String number = this.getRawUltrasonicReading().substring(1, 5);
			return Double.parseDouble(number)/1000;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
