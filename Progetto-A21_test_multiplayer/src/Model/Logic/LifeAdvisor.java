package Model.Logic;

import Model.Core.Screen;
import Model.Items.Ball;

public class LifeAdvisor {
	
	private CollisionAdvisor collision;
	private Ball ball;
	private Screen screen;
	private int life;
	
	public LifeAdvisor(Screen screen, CollisionAdvisor collision, Ball ball) {
		
		this.collision = collision;
		this.ball = ball;
		this.screen = screen;
		life = 3;
	}
	
	// false se è ancora in vita, true se ha perso
	
	public boolean checkLife() {
		if(collision.checkGameOver()) {
			life -= 1;
			if(life < 1) return true;
			ball.refresh();
		}
		return false;
		}
	
	public void resetLife() {
		life=3;
	}
	
	public int getLife() {
		return life;
	}
		
}
	
	


