package org.usfirst.frc.team321.robot.subsystems;

import org.usfirst.frc.team321.robot.RobotMap;
import org.usfirst.frc.team321.robot.commands.RegulateCompressor;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
	
	public DoubleSolenoid leftClimberToggle, rightClimberToggle;
	public Compressor compressor;
	
	public Pneumatics(){
		leftClimberToggle = new DoubleSolenoid(4,5);
		rightClimberToggle = new DoubleSolenoid(6,7);

		compressor = new Compressor(RobotMap.COMPRESSOR);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RegulateCompressor());
	}
	
	
	
	public void regulateCompressor(){
    	if(!compressor.getPressureSwitchValue() && !compressor.enabled()
    			&& isCompressorSafeToUse()){
    		compressor.start();
    	}else if(compressor.getPressureSwitchValue() && compressor.enabled() 
    			|| !isCompressorSafeToUse()){
    		compressor.stop();
    	}
    }
	
	public boolean isCompressorSafeToUse(){	
		if((compressor.getCompressorCurrentTooHighFault() && !compressor.getCompressorCurrentTooHighStickyFault()) ||
  			(compressor.getCompressorNotConnectedFault() && !compressor.getCompressorNotConnectedStickyFault()) || 
  			(compressor.getCompressorShortedFault() && !compressor.getCompressorShortedStickyFault())){
			return false;
	   	}else{
	   		return true;
		}
	}	
	
	public boolean getPressure(){
		return compressor.getPressureSwitchValue();
	}
	
}
