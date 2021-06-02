package Model.Items.PowerUp;

import Model.Items.Ball;
import Model.Items.ScreenItem;
import Model.Items.Utilities;

public class BallSpeedUp extends PowerUp {
	
	public BallSpeedUp(ScreenItem screenItem) {
		super.affectedScreenItem.add(screenItem);
	}

	@Override
	public void activate() {
		((Ball)affectedScreenItem.get(0)).incrSpeed();
		this.setActive(true);
	}

	@Override
	public void disactivate() {
		((Ball)affectedScreenItem.get(0)).setSpeed(Utilities.DEFAULT_SPEED);
		this.setActive(false);
	}

	@Override
	public PowerUpTypes whichPower() {
		return PowerUpTypes.FAST;
	}
}
