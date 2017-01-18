package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LauncherSequenceLaunchBall extends CommandGroup {

    public LauncherSequenceLaunchBall() {
        addSequential(new LauncherLaunch(5050));
        addSequential(new LauncherWaitForStabilize());
        addSequential(new LauncherStop());
    }
}
