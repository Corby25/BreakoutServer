package Model.Items.PowerUp;

import Model.Items.Ball;
import Model.Items.ScreenItem;
import Model.Items.Utilities;

public class BallSpeedUp extends PowerUp {
	
	public BallSpeedUp(ScreenItem screenItem) {
		super.affectedScreenItem = screenItem;
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
}
