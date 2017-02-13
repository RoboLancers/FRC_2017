package org.usfirst.frc.team321.robot.commands;

import java.io.IOException;
import java.net.UnknownHostException;

import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SSHStartCamera extends Command {
	
	Shell shell;
	boolean isDone;

    public SSHStartCamera() {
        try {
			shell = new SSHByPassword("10.3.21.5", 22, "ubuntu", "321lancers");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        isDone = false;
    }

    protected void initialize() {
    	try {
			new Shell.Plain(shell).exec("/home/ubuntu/FRC.sh");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	isDone = true;
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
