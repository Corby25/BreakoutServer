package Model.Logic;

import Model.Items.Ball;
//import Music.Music;
//import Music.MusicTypes;
import Utility.Utilities;

public class LifeAdvisor {
	
	//private Music loseLifeMusic;
	private CollisionAdvisor collision;
	private Ball ball;
    private int life;
	
	public LifeAdvisor(/*Music loseLife,*/ CollisionAdvisor collision, Ball ball) {
		
		//this.loseLifeMusic = loseLife;
		this.collision = collision;
		this.ball = ball;
        life = Utilities.NUMBER_LIFE;
	}
	
	
	// false se è ancora in vita, true se ha perso
	
	public boolean checkLife() {
		if(collision.checkGameOver()) {
			//if (loseLifeMusic.isMusicOn() && getLife() > 1) loseLifeMusic.playMusic(MusicTypes.LOSE_LIFE);
            life -= 1;
			if(life < 1) return true;
			ball.refresh();
		}
		return false;	
	}
	/*
	public void resetLife() {
        life=3;
    }
*/
    public int getLife() {
        return life;
    }
}
	
	


