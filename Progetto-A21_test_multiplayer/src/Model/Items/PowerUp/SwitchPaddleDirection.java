package Model.Items.PowerUp;

import java.util.ArrayList;

import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Logic.Player;

public class SwitchPaddleDirection extends PowerUp {
	
	public SwitchPaddleDirection(ArrayList<Paddle> paddles) {
		super.affectedScreenItem = paddles;
	}

	@Override
	public void activate() {
		((Paddle)affectedScreenItem).switchDir();
		this.setActive(true);
	}

	@Override
	public void disactivate() {
			((Paddle)affectedScreenItem).switchDir();
			this.setActive(false);
	}
	
	public PowerUpTypes whichPower() {
		return PowerUpTypes.FLIP;
	}
}
