package Model.Items.PowerUp;

import Model.Items.ScreenItem;

public abstract class PowerUp {
	
	protected boolean active;
	protected ScreenItem affectedScreenItem;
	protected double duringTime;
	private double timeStart;
	
	public PowerUp() {
		active = false;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active ;
	}
	
	public void startPowerUp() {
		this.activate();
		new Thread(new Runnable(){
			@Override
			public synchronized void run(){
				timeStart = System.nanoTime();
				while(System.nanoTime() <= timeStart + duringTime);
				disactivate();
			}
		}).start();
		
	}
	
	public abstract void activate();
	
	public abstract void disactivate();
	
	public abstract PowerUpTypes whichPower();
	
	public abstract String getPath();
}
