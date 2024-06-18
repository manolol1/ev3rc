package remotecontrol;

import lejos.hardware.Button;

public class ExitButtonController implements Runnable {
	public void run() {
		Button.ESCAPE.waitForPressAndRelease();
		System.exit(0);
	}
}
