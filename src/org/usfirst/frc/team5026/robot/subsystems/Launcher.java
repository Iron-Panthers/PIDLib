package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.LauncherStop;
import org.usfirst.frc.team5026.robot.lib.PIDMotorGroup;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Launcher extends Subsystem {

    public PIDMotorGroup group;
    public int launcherSpeed;

    public Launcher(int launcherSpeed) {
    	group = Robot.hardware.launcherGroup;
    	this.launcherSpeed = launcherSpeed;
    }
    public void launchBall() {
    	group.setTarget(launcherSpeed);
    }
    public void setTarget(int launcherSpeed) {
    	this.launcherSpeed = launcherSpeed;
    }
    public void update() {
    	group.update();
    }
    public void stop() {
    	group.slowStop();
    }
    public void resetTarget() {
    	group.setTarget(0);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new LauncherStop());
    }
}

