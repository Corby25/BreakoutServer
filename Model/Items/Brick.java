package Model.Items;

import java.awt.image.BufferedImage;

public class Brick extends ScreenItem{
	
	protected int hitLevel = 4;
	private int initialHitLevel;
	protected boolean destroyed;
	
	/*
	 * Costruttore 1: Brick normale
	 */
	public Brick(int width, int height, int[] position) { 
		super(width, height, position);
		this.destroyed = false;
		initialHitLevel = hitLevel;
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
	
	public void setHitLevel(int hitLevel) {
		this.hitLevel = hitLevel;
	}

	
	public void setImage(BufferedImage imageUpdated) {
		this.image = imageUpdated;
	}
	
	public void refresh() {
		destroyed = false;
		this.hitLevel = initialHitLevel ;
	}
	
	public boolean activatePowerUP() {
		return false;
	}
}