package Model.Items.PowerUp;

import java.util.ArrayList;

import Model.Items.Paddle;
import Model.Items.ScreenItem;

public class LongerPaddle extends PowerUp {
	
	private String path = "/Images/paddle.png";
    private ArrayList<Paddle> paddles;
	
	public LongerPaddle(ArrayList<Paddle> paddles) {
		this.paddles = paddles;
		duringTime = 5e9;
	}

	@Override
	public void activate() {
		for (Paddle tempPaddle : paddles) {
			tempPaddle.setImageWidth(tempPaddle.getImageWidth() + 30);
		}
		this.setActive(true);
	}

	@Override
	public void disactivate() {
		for (Paddle tempPaddle : paddles) {
			tempPaddle.setImageWidth(tempPaddle.getImageWidth() - 30);
		}
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
