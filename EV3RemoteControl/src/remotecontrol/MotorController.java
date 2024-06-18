package remotecontrol;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class MotorController {
	private int speed;
	private final RegulatedMotor motorLeft, motorRight;
	
	public MotorController(Port motorPortLeft, Port motorPortRight, int speed) {
		this.speed = speed;
		
		// initialize motors
		motorLeft = new EV3LargeRegulatedMotor(motorPortLeft);
		motorRight = new EV3LargeRegulatedMotor(motorPortRight);
		
	}
	
	public void stop() {
		motorLeft.stop();
		motorRight.stop();
	}
	
	public void forward() {
		motorLeft.setSpeed(speed * 10);
		motorRight.setSpeed(speed * 10);
		
		motorLeft.forward();
		motorRight.forward();
	}
	
	public void backward() {
		motorLeft.setSpeed(speed * 10);
		motorRight.setSpeed(speed * 10);
		
		motorLeft.backward();
		motorRight.backward();
	}
	
	public void left() {
		motorLeft.setSpeed(speed * 5);
		motorRight.setSpeed(speed * 15);
		
		motorLeft.forward();
		motorRight.forward();
	}
	
	public void right() {
		motorLeft.setSpeed(speed * 15);
		motorRight.setSpeed(speed * 5);
		
		motorLeft.forward();
		motorRight.forward();
	}
	
	public void forwardRight() {
		motorLeft.setSpeed(speed * 12);
		motorRight.setSpeed(speed * 8);
		
		motorLeft.forward();
		motorRight.forward();
	}
	
	public void forwardLeft() {
		motorLeft.setSpeed(speed * 8);
		motorRight.setSpeed(speed * 12);
		
		motorLeft.forward();
		motorRight.forward();
	}
	
	public void backwardLeft() {
		motorLeft.setSpeed(speed * 8);
		motorRight.setSpeed(speed * 12);
		
		motorLeft.backward();
		motorRight.backward();
	}
	
	public void backwardRight() {
		motorLeft.setSpeed(speed * 12);
		motorRight.setSpeed(speed * 8);
		
		motorLeft.backward();
		motorRight.backward();
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
