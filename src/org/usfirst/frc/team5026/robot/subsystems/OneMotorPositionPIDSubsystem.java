package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.lib.Constants;
import org.usfirst.frc.team5026.robot.lib.ToleranceType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class OneMotorPositionPIDSubsystem extends PIDSubsystem {
	public ToleranceType toleranceType;
	public double tolerance;
	public Encoder encoder;
	public SpeedController motor;

    public OneMotorPositionPIDSubsystem(String name, double p, double i, double d, double f, double period, ToleranceType t, double tolerance, Encoder e, SpeedController motor) {
		super(name, p, i, d, f, period);
		toleranceType = t;
		this.tolerance = tolerance;
		this.motor = motor;
		encoder = e; // Somehow setup config per pulse
		if (t == ToleranceType.Absolute) {
			setAbsoluteTolerance(tolerance);
		} else if (t == ToleranceType.Percentage) {
			setPercentTolerance(tolerance);
		}
    }
    public OneMotorPositionPIDSubsystem() {
    	// Initializes everything from Constants instead
    	super(Constants.PID_SUBSYSTEM_NAME, Constants.PID_SUBSYSTEM_P, Constants.PID_SUBSYSTEM_I, Constants.PID_SUBSYSTEM_D, Constants.PID_SUBSYSTEM_F, Constants.PID_SUBSYSTEM_PERIOD);
    	
    }

	@Override
	protected double returnPIDInput() {
		// Return the encoder value of an encoder
		return encoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		//Set the motor here
		motor.pidWrite(output);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

