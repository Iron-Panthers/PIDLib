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
	
	private double lastExponentialError = 100000;
	private double currentExponentialError = 0;
	
	double rpm;
	
	/*
	 * Alpha for exponential scaling
	 * 0 < a < 1
	 */
	double alpha = 0.15;
	double range;
	
	boolean finished = false;

    public LauncherWaitForStabilize() {
        requires(Robot.launcher);
    }

    protected void initialize() {
    	launcher = Robot.launcher;
    	rpm = launcher.launcherSpeed;
    	range = Math.abs(launcher.launcherSpeed * Constants.LAUNCHER_TOLERANCE * launcher.group.getTicks() / 600);
    }

    protected void execute() {
    	currentExponentialError = alpha * Math.abs(launcher.group.getEncTalon().getClosedLoopError()) + (1 - alpha) * lastExponentialError;
    	if(Math.abs(currentExponentialError) < range) {
    		System.out.println("Stable!: " + currentExponentialError + " < " + range);
    		finished = true;
    	}
    	lastExponentialError = currentExponentialError;
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}
