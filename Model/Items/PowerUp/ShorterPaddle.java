package Model.Items.PowerUp;

import Model.Items.Paddle;
import Model.Items.ScreenItem;

public class ShorterPaddle extends PowerUp {
	
    private String path = "/Images/paddle.png";
	
	public ShorterPaddle(ScreenItem screenItem) {
		super.affectedScreenItem = screenItem;
		duringTime = 20e9;
	}

	@Override
	public void activate() {
		((Paddle)affectedScreenItem).setImageWidth(affectedScreenItem.getImageWidth() - 30);
		this.setActive(true);
	}

	@Override
	public void disactivate() {
		((Paddle)affectedScreenItem).setImageWidth(affectedScreenItem.getImageWidth() + 30);
		this.setActive(false);
	}

	@Override
	public PowerUpTypes whichPower() {
		return PowerUpTypes.SHORT;
	}

	@Override
	public String getPath() {
		return path;
	}

}
