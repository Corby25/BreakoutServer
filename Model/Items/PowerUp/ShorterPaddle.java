package Model.Items.PowerUp;

import java.util.ArrayList;

import Model.Items.Paddle;
import Model.Items.ScreenItem;

public class ShorterPaddle extends PowerUp {
	
    private String path = "/Images/paddle.png";
    private ArrayList<Paddle> paddles;
	
	public ShorterPaddle(ArrayList<Paddle> paddles) {
		this.paddles = paddles;
		duringTime = 20e9;
	}

	@Override
	public void activate() {
		for (Paddle tempPaddle : paddles) {
			tempPaddle.setImageWidth(tempPaddle.getImageWidth() - 30);
		}
		this.setActive(true);
	}

	@Override
	public void disactivate() {
		for (Paddle tempPaddle : paddles) {
			tempPaddle.setImageWidth(tempPaddle.getImageWidth() + 30);
		}
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
