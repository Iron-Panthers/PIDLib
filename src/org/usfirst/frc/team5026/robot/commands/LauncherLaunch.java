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
	int rpm;

    public LauncherLaunch(int rpm) {
        requires(Robot.launcher);
        this.rpm = rpm;
    }

    protected void initialize() {
    	launcher = Robot.launcher;
    	launcher.setTarget(rpm);
    	launcher.launchBall();
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
