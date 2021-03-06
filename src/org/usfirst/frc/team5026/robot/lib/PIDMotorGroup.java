package org.usfirst.frc.team5026.robot.lib;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class PIDMotorGroup {

	CANTalon[] talons;
	int encoderPort;
	int numEncoders;
	int target = 0;
	int encTicks;
	boolean prints;
	double currentExponentialError = 0;
	double lastExponentialError;
	
	public PIDMotorGroup(int[] deviceNumbers, boolean[] inverted, int encTicks, double[] pidf, int pidProfile) {	
		talons = new CANTalon[deviceNumbers.length];
		// All other CANTalons should be followers of the encoderPort CANTalon
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new CANTalon(deviceNumbers[i]);
		}
		checkEncoderPort();
		// Setup the encoder CANTalon to have the correct values.
		setupTalons(inverted, pidf, pidProfile);
		this.encTicks = encTicks;
		prints = true;
	}
	// TODO: Fix device numbers to take in speedcontrollers
	public PIDMotorGroup(int[] deviceNumbers, boolean[] inverted, int encTicks, double[] pidf, int pidProfile, boolean prints) {	
		talons = new CANTalon[deviceNumbers.length];
		// All other CANTalons should be followers of the encoderPort CANTalon
		for (int i = 0; i < talons.length; i++) {
			talons[i] = new CANTalon(deviceNumbers[i]);
		}
		checkEncoderPort();
		// Setup the encoder CANTalon to have the correct values.
		setupTalons(inverted, pidf, pidProfile);
		this.encTicks = encTicks;
		this.prints = prints;
	}
	public void setupTalons(boolean[] inverted, double[] pidf, int pidProfile) {
		// This can be found in Sec. 17.2.3 in the TalonSRX Software Manual
    	talons[encoderPort].setProfile(pidProfile);
		talons[encoderPort].setF(pidf[3]);
    	talons[encoderPort].setP(pidf[0]);
    	talons[encoderPort].setI(pidf[1]);
    	talons[encoderPort].setD(pidf[2]);
    	// Sec. 17.2.3 (Software Reference Manual)
    	talons[encoderPort].reverseSensor(inverted[encoderPort]);
    	if (encTicks == 4096) {
    		// For a quadature encoder (Versaplanetary encoders)
    		talons[encoderPort].setFeedbackDevice(FeedbackDevice.QuadEncoder);
    		talons[encoderPort].configEncoderCodesPerRev((int) (0.25*encTicks)); // 1/4 * 4096 (CAUSE 4096 IS THE NUMBER OF TICKS PER REV. MEASURED. F U MANUAL (check position changes (big number in selftest))
    	} else {
    		// TODO: Add Grayhill Encoder mappings
    		talons[encoderPort].setFeedbackDevice(FeedbackDevice.AnalogEncoder);
    		talons[encoderPort].configEncoderCodesPerRev(encTicks);
    	}
    	talons[encoderPort].setPosition(0);
		talons[encoderPort].changeControlMode(TalonControlMode.Speed);
		talons[encoderPort].disable();
		
		for (int i = 0; i < talons.length; i++) {
			// Get Encoder port, make sure everything else is follower
	    	// Sec. 12.1.3 (Software Reference Manual)
			talons[i].setForwardSoftLimit(+15.0);
			talons[i].setReverseSoftLimit(-15.0);
			talons[i].configNominalOutputVoltage(+0.0f, -0.0f);
	    	talons[i].configPeakOutputVoltage(+12.0f, -12.0f);
	    	if (i != encoderPort) {
	    		// Make sure all of the other Talons are followers of the encoder talon
		    	talons[i].setProfile(pidProfile);
		    	talons[i].changeControlMode(TalonControlMode.Follower);
		    	talons[i].reverseOutput(inverted[i]);
		    	talons[i].set(talons[encoderPort].getDeviceID()); // Follows the encoder CANTalon
	    	}
		}
	}
	
	public int getTicks() {
		return encTicks;
	}
	
	public CANTalon getEncTalon() {
		return talons[encoderPort];
	}
	
	public void checkEncoderPort() {
		// Finds the CANTalon with an encoder out of all of the CANTalons given
		// If there are multiple CANTalons with encoders, then the last one is the designated motor controller
		
		int count = 0;
		int ePort = -1; // Temporary encoder port
		for (int i = 0; i < talons.length; i++) {
			CANTalon c = talons[i];
			try {
				System.out.println(c.getClosedLoopError());
				count ++;
				ePort = i;
			}
			catch (InstantiationError e) {
				// No encoder!
				// Add in something to not do PID!
			}
		}
		encoderPort = ePort;
		numEncoders = count;
	}
	
	public void set(double power) {
		talons[encoderPort].set(power);
	}
	
	public void set(double power, int port) {
		// DON'T USE THIS UNLESS SETTING A NEW FOLLOWER
		talons[port].set(power);
	}
	
	public void setTarget(int target) {
		// Sets the overall PID Target
		this.target = target;
		if (encTicks == 4096) {
			lastExponentialError = target * encTicks * 0.25;
		} else {
			lastExponentialError = target * encTicks;
		}
	}
	
	public void update() {
		// To be called as fast as possible (in the execute of a command)
		// Runs the PID and changes the motor output
		PID(target);
	}
	
	private void PID(int target) {
		if (numEncoders == 1) {
			// Encoder port is the correct port!
			go(target);
		}
		else if (numEncoders > 1) {
			// TOO MANY!
			System.out.println("Too many Encoders! (Currently have " + numEncoders+")");
		}
		else {
			// No encoders found
			System.out.println("NO ENCODERS DETECTED!");
		}
		talons[encoderPort].enable();
	}
	
	private void go(int target) {
		talons[encoderPort].changeControlMode(TalonControlMode.Speed);
		talons[encoderPort].set(target);
		if (prints) 
		prints();
	}
	
	private void prints() {
		// Prints out a bunch of information about the CANTalons
		String sb = "";
		sb += "MotorOutputVoltage ";
    	sb += talons[encoderPort].getOutputVoltage() / talons[encoderPort].getBusVoltage();
    	sb += " CurrentSpeed ";
    	sb += talons[encoderPort].getSpeed();// / 1440 * 600; //Ticks to RPM
    	sb += " ClosedLoopError ";
		sb += talons[encoderPort].getClosedLoopError();
		sb += " Target ";
		sb += target;
		System.out.println(sb);
	}
	
	public boolean isStable(double alpha, double range) {
		currentExponentialError = alpha * Math.abs(getEncTalon().getClosedLoopError()) + (1 - alpha) * lastExponentialError;
    	if(Math.abs(currentExponentialError) < range) {
//    		System.out.println("Stable!: " + currentExponentialError + " < " + range);
    		return true;
    	}
    	lastExponentialError = currentExponentialError;
    	return false;
	}
	
	public void slowStop() {
		talons[encoderPort].disable();
	}
}
