package remotecontrol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.utility.Delay;

public class BluetoothController {
	BTConnector btConnector;
    BTConnection connection;
    
    DataInputStream dis;
    DataOutputStream dos;
    
    public BluetoothController() {
		btConnector = new BTConnector();
	}
    
    public void connect(String device) {
    	LCD.drawString("Connecting...", 0, 1);
    	connection = btConnector.connect(device, NXTConnection.RAW);
    	
    	Delay.msDelay(100);

        if (connection == null) {
            LCD.clear();
            LCD.drawString("Failed to connect.", 0, 3);
            LCD.drawString("Please restart!", 0, 4);
            Button.ESCAPE.waitForPressAndRelease();
            System.exit(0);
        } else {
            LCD.clear();
            LCD.drawString("Connected!", 0, 4);
            Delay.msDelay(1000);
        }

        dis = connection.openDataInputStream();
        dos = connection.openDataOutputStream();
    }
    
    /** 
     * Read message from Bluetooth connection.
     * @return received message or -1 if no new message is available
     */
    public int readInt() {
    	try {
            if (dis.available() > 0) {
                return dis.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LCD.clear(3);
            LCD.drawString(e.toString(), 0, 3);
        }
    	
    	return -1; // no message available
    }
}
