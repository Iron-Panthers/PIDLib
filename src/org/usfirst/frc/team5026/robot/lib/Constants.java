package org.usfirst.frc.team5026.robot.lib;

public class Constants {
	// PID Constants for the launcher (in this case, the only subsystem)
	// In actuality, every subsystem that has PID would have different PID Constants
	// The following MUST ALWAYS BE TESTED before determining the values
	// To test the values, run the motor at ~85% power, and determine how long it takes to stabilize
	// Picture a PID curve for reference (google it)
	
	public static final boolean LAUNCHER_IS_VERSA = true;
	public static final double PID_LAUNCHER_P = 0.0;
	public static final double PID_LAUNCHER_I = 0.0;
	public static final double PID_LAUNCHER_D = 0.0;
	public static final double PID_LAUNCHER_F = 0.0;
	// Number of encoder ticks for one revolution.
	public static final int PID_LAUNCHER_ENCODER_REV = 0;
	public static final double LAUNCHER_TOLERANCE = 0.02; // Percentage of tolerance for PID to be stable
}