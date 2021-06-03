package Model.Logic;

import Model.Core.Screen;
import Model.Items.Ball;

public class LifeAdvisor {
	
	private Player p;
	private CollisionAdvisor collision;
	private Ball ball;
	
	public LifeAdvisor(Player p, CollisionAdvisor collision, Ball ball) {
		
		this.p = p;
		this.collision = collision;
		this.ball = ball;
	}
	
	// false se è ancora in vita, true se ha perso
	
	public boolean checkLife() {
		if(collision.checkGameOver()) {
			p.loseLife();
			if(p.getLife() < 1) return true;
			ball.refresh();
		}
		return false;
		}
	
	public void resetLife() {
		
		p.resetLife();
	}
		
	}
	
	


