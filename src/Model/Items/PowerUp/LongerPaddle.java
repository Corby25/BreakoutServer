package Model.Items.PowerUp;

import Model.Items.Paddle;
import Model.Items.ScreenItem;

public class LongerPaddle extends PowerUp {
	
	private String path = "/Images/paddle.png";
	
	public LongerPaddle(ScreenItem screenItem) {
		super.affectedScreenItem = screenItem;
		duringTime = 20e9;
	}

	@Override
	public void activate() {
		((Paddle)affectedScreenItem).setImageWidth(affectedScreenItem.getImageWidth() + 30);
		this.setActive(true);
	}

	@Override
	public void disactivate() {
		((Paddle)affectedScreenItem).setImageWidth(affectedScreenItem.getImageWidth() - 30);
		this.setActive(false);
	}

	@Override
	public PowerUpTypes whichPower() {
		return PowerUpTypes.LONG;
	}

	@Override
	public String getPath() {
		return path;
	}
	
	

}
