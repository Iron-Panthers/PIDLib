package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.lib.Constants;
import org.usfirst.frc.team5026.robot.subsystems.Launcher;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LauncherWaitForStabilize extends Command {
	
	Launcher launcher;
		
	/*
	 * Alpha for exponential scaling
	 * 0 < a < 1
	 */
	double alpha = 0.15;
	double range;
	
    public LauncherWaitForStabilize() {
        requires(Robot.launcher);
    }

    protected void initialize() {
    	launcher = Robot.launcher;
    	range = Math.abs(launcher.launcherSpeed * Constants.LAUNCHER_TOLERANCE * launcher.group.getTicks() / 600);
    }

    protected void execute() {
    	launcher.update();
    }

    protected boolean isFinished() {
        return launcher.isStable(alpha, range);
    }

    protected void end() {
    	launcher.stop();
    }

    protected void interrupted() {
    	launcher.stop();
    }
}
