package org.usfirst.frc.team5026.robot.lib;

public class Constants {
	// PID Constants for the launcher (in this case, the only subsystem)
	// In actuality, every subsystem that has PID would have different PID Constants
	// The following MUST ALWAYS BE TESTED before determining the values
	// To test the values, run the motor at ~85% power, and determine how long it takes to stabilize
	// Picture a PID curve for reference (google it)
	
	public static final int LAUNCHER_RPM = 5050; // Start speed
	public static final double LAUNCHER_TOLERANCE = 0.02; // Percentage of tolerance for PID to be stable
	// Number of encoder ticks for one revolution.
	// This is 4096 for a quadature
	// This is ONE of the following for GrayHill: 25, 32, 50, 64, 100, 128, 256
	public static final int LAUNCHER_ENCODER_REV = 4096;
	public static final double PID_LAUNCHER_P = 0.0;
	public static final double PID_LAUNCHER_I = 0.0;
	public static final double PID_LAUNCHER_D = 0.0;
	public static final double PID_LAUNCHER_F = 0.0;
}