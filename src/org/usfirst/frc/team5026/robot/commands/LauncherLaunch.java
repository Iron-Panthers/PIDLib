package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Launcher;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command sets the target for PID. The next command handles the stabilization
 * for PID.
 */
public class LauncherLaunch extends Command {
	
	Launcher launcher;

    public LauncherLaunch() {
        requires(Robot.launcher);
    }

    protected void initialize() {
    	launcher = Robot.launcher;
    	launcher.resetTarget();
    }

    protected void execute() {
    	launcher.update();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	// Don't stop when it ends
    }

    protected void interrupted() {
    	// Stop when it interrupts
    	launcher.stop();
    }
}
