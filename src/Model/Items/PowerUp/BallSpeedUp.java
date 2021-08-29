package Model.Items.PowerUp;

import Model.Items.Ball;
import Model.Items.ScreenItem;
import Utility.Utilities;

public class BallSpeedUp extends PowerUp {
	
	private String path = "/Images/fast.png";
	
	public BallSpeedUp(ScreenItem screenItem) {
		affectedScreenItem = screenItem;
		duringTime = 10e9;
	}

	@Override
	public void activate() {
		((Ball)affectedScreenItem).incrSpeed();
		this.setActive(true);
	}

	@Override
	public void disactivate() {
		((Ball)affectedScreenItem).setSpeed(Utilities.DEFAULT_SPEED);
		this.setActive(false);
	}

	@Override
	public PowerUpTypes whichPower() {
		return PowerUpTypes.FAST;
	}
	
	public String getPath() {
		return path;
	}
}
