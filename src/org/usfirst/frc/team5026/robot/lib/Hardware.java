package org.usfirst.frc.team5026.robot.lib;

import org.usfirst.frc.team5026.robot.RobotMap;

public class Hardware {
	public PIDMotorGroup launcherGroup;
	
	public Hardware() {
		int[] motorPorts = RobotMap.LAUNCHER_MOTOR_PORTS;
    	boolean[] inverted = RobotMap.LAUNCHER_MOTOR_INVERTED;
    	double[] pidf = {Constants.PID_LAUNCHER_P, Constants.PID_LAUNCHER_I, Constants.PID_LAUNCHER_D, Constants.PID_LAUNCHER_F};
    	launcherGroup = new PIDMotorGroup(motorPorts, inverted, Constants.LAUNCHER_ENCODER_REV, pidf, 0);
	}
}
