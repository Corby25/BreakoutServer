package Model.Items;

import GUI.ImagesLoader;
import Model.Items.PowerUp.PowerUp;

public class BrickPowerUp extends Brick {
	
	private PowerUp powerUp;

	public BrickPowerUp(int width, int height, int[] position, PowerUp powerUp) {
		super(width, height, position);
		this.powerUp = powerUp;
		this.image = ImagesLoader.getInstace().uploadImage(powerUp.getPath());
		hitLevel = 1;
	}

	public boolean activatePowerUP() {
		if(powerUp != null && !powerUp.isActive()) {
			powerUp.startPowerUp();
			return true;
		}
		return false;
	}
	
	public void disactivatePowerUp() {
		if(powerUp != null && powerUp.isActive()) powerUp.disactivate();
	}
	
	public boolean hasActivePowerUp() {
		return powerUp.isActive();
	}
	
	public PowerUp getPowerUp() {
		return powerUp;
	}

}
