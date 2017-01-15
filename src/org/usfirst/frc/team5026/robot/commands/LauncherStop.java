package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Launcher;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Handles the stopping of the Launcher subsystem
 */
public class LauncherStop extends Command {

	Launcher launcher;
    public LauncherStop() {
        requires(Robot.launcher);
    }

    protected void initialize() {
    	launcher = Robot.launcher;
    }

    protected void execute() {
    	launcher.stop();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	// Not needed
    }

    protected void interrupted() {
    	// Not needed
    }
}
