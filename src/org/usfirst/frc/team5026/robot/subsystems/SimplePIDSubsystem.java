package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.lib.ToleranceType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class SimplePIDSubsystem extends PIDSubsystem {
	public ToleranceType toleranceType;
	public double tolerance;
	public Encoder e;

    public SimplePIDSubsystem(String name, double p, double i, double d, double f, double period, ToleranceType t, double tolerance) {
		super(name, p, i, d, f, period);
		toleranceType = t;
		this.tolerance = tolerance;
		if (t == ToleranceType.Absolute) {
			setAbsoluteTolerance(tolerance);
		} else if (t == ToleranceType.Percentage) {
			setPercentTolerance(tolerance);
		}
    }

	@Override
	protected double returnPIDInput() {
		// Return the encoder value of an encoder
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		//Set the motor here
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

