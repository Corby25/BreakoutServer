package Model.Items.PowerUp;

import Model.Items.Paddle;
import Model.Items.ScreenItem;

public class SwitchPaddleDirection extends PowerUp {
	
	public SwitchPaddleDirection(ScreenItem screenItem) {
		super.affectedScreenItem = screenItem;
	}

	@Override
	public void activate() {
		((Paddle)affectedScreenItem).switchDir();
		this.setActive(true);
	}

	@Override
	public void disactivate() {
			((Paddle)affectedScreenItem).switchDir();
			this.setActive(false);
	}
	
	public PowerUpTypes whichPower() {
		return PowerUpTypes.FLIP;
	}
}
