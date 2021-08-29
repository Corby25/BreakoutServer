package Model.Items;

import java.awt.image.BufferedImage;

import GUI.ImagesLoader;


public class Brick extends ScreenItem{
	
	private BufferedImage[] images;
	protected int hitLevel = 4;
	private int initialHitLevel;
	protected boolean destroyed;
	
	/*
	 * Costruttore 1: Brick normale
	 */
	public Brick(int width, int height, int[] position) { 
		super(width, height, position);
		images = new BufferedImage[hitLevel];
		if(hitLevel == 4) initialize();
		this.destroyed = false;
		initialHitLevel = hitLevel;
	}
	
	private void initialize() {
		
		images[0] = ImagesLoader.getInstace().uploadImage("/Images/brick.png");
		images[1] = ImagesLoader.getInstace().uploadImage("/Images/brick1.png");
		images[2] = ImagesLoader.getInstace().uploadImage("/Images/brick2.png");
		images[3] = ImagesLoader.getInstace().uploadImage("/Images/brick3.png");
		
		this.image = images[0];
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void hit() {
		hitLevel -= 1;
		if (hitLevel == 0) {
			destroyed = true;
		} else image = images[4-hitLevel];
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