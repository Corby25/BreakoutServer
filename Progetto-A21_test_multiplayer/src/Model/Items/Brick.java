package Model.Items;

import java.awt.image.BufferedImage;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.PowerUpTypes;

public class Brick extends ScreenItem{
	
	private int hitLevel;
	private int initialHitLevel;
	protected boolean destroyed;
	private PowerUp powerUp; // null vuol dire che è un brick normale
	private boolean hasPowerUp; 
	
	/*
	 * Costruttore 1: Brick normale
	 */
	public Brick(BufferedImage image, int width, int height, int[] position, int hitLevel) {
		super(image, width, height, position);
		this.destroyed = false;
		this.hitLevel = hitLevel;
		initialHitLevel = hitLevel;
		hasPowerUp = false;
	}
	
	/*
	 * Costruttore 2: Special Brick - PowerUP
	 */
	public Brick(BufferedImage image, int width, int height, int[] position, int hitLevel, PowerUp powerUp) {
		super(image, width, height, position);
		this.destroyed = false;
		this.hitLevel = hitLevel;
		initialHitLevel = hitLevel;
		this.powerUp = powerUp;
		hasPowerUp = true;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void hit() {
		hitLevel -= 1;
		if (hitLevel == 0) {
			destroyed = true;
		}
	}
	
	public int getHitLevel() {
		return hitLevel;
	}
	
	public void setImage(BufferedImage imageUpdated) {
		this.image = imageUpdated;
	}
	
	public void refresh() {
		destroyed = false;
		this.hitLevel = initialHitLevel ;
	}
	
	public boolean activatePowerUP() {
		if(powerUp != null && !powerUp.isActive()) {
			powerUp.activate();
			return true;
		}
		return false;
	}
	
	public void disactivatePowerUp() {
		if(powerUp != null && powerUp.isActive()) powerUp.disactivate();
	}
	
	public boolean getHasPowerUp() {
		return hasPowerUp;
	}
	
	public PowerUpTypes whichPower() {
		if(powerUp != null) return powerUp.whichPower();
		else return PowerUpTypes.NULL;
	}
}
	
