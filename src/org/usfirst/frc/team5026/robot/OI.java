package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.commands.LauncherSequenceLaunchBall;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick stick;
	
	JoystickButton launch;
	public OI () {
		stick = new Joystick(0);
		initButtons();
	}
	public void initButtons() {
		launch = new JoystickButton(stick, 1);
	}
	public void mapButtons() {
		launch.whenPressed(new LauncherSequenceLaunchBall());
	}
}
