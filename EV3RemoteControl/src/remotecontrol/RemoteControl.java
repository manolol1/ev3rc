package remotecontrol;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class RemoteControl {
    public static void main(String[] args) {
        // Exit program when exit button is pressed
        Thread exitThread = new Thread(new ExitButtonController());
        exitThread.setDaemon(true);
        exitThread.start();
        
        MotorController mc = new MotorController(MotorPort.A, MotorPort.D, 25);
        
        BluetoothController bt = new BluetoothController();
        bt.connect("ESP32_rc");
        Delay.msDelay(100);
        
        LCD.clear();
        LCD.drawString("SSID: EV3RC", 0, 3);
        LCD.drawString("PWD: 12345678", 0, 4);
        LCD.drawString("192.168.4.1:80", 0, 5);
        
        controlLoop(mc, bt);
    }
    
    private static void controlLoop(MotorController mc, BluetoothController bt) {
    	for(;;) {
    		int code = bt.readInt();
    		
    		switch (code) {
    			case 0:
    				mc.stop();
    				break;
    			case 1:
    				mc.forward();
    				break;
    			case 2:
    				mc.backward();
    				break;
    			case 3:
    				mc.left();
    				break;
    			case 4:
    				mc.right();
    				break;
    			case 5:
    				mc.forwardLeft();
    				break;
    			case 6:
    				mc.forwardRight();
    				break;
    			case 7:
    				mc.backwardLeft();
    				break;
    			case 8:
    				mc.backwardRight();
    				break;
    		}
    	}
    }
}
