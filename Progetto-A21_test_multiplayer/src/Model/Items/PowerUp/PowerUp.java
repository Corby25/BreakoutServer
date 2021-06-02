package Model.Items.PowerUp;

import java.util.ArrayList;

import Model.Items.ScreenItem;

public abstract class PowerUp {
	
	protected boolean active;
	protected ArrayList affectedScreenItem;
	
	public PowerUp() {
		active = false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active ;
	}
	
	public abstract void activate();
	
	public abstract void disactivate();
	
	public abstract PowerUpTypes whichPower();
}
