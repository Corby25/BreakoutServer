package Model.Logic;

import Model.Items.Ball;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
//import Music.Music;
//import Music.MusicTypes;
import Utility.Utilities;

public class CollisionAdvisor {
	Ball ball;
	//Music collisionMusic;
	int ballSpeed;
	

	public CollisionAdvisor(Ball ball /*,Music collisionMusic*/) {
		this.ball = ball;
		//this.collisionMusic = collisionMusic;
		this.ballSpeed = ball.getSpeed()-1;
		
	}
	
	public boolean checkGameOver(int numberOfPlayers) {
		if((ball.getPosition()[1] + ball.getImageHeight()) >= Utilities.SCREEN_HEIGHT-3) {
            return true;
        }
		if(numberOfPlayers > 1) {
			if (ball.getPosition()[1] <= 3) return true;
		}
		return false;
	}
	
	public boolean checkBorderCollision(int numberOfPlayers) {
        if (ball.getPosition()[0] <= 0) {
            ball.setXdir(1);
			//if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
        }
        
        if(numberOfPlayers > 1) {
        	if (ball.getPosition()[1] <= 0) return false;
        }
        else {
        	if (ball.getPosition()[1] <= 0) {
                ball.setYdir(1);
        		//if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
            }
        }
    
        if((ball.getPosition()[1] + ball.getImageHeight()) >= Utilities.SCREEN_HEIGHT) {
               return false;
        }
        else return true;
 
    }
	
	public boolean checkCollisionLato(ScreenItem item) {
		this.ballSpeed = ball.getSpeed();
		if ((ball.getPosition()[1] + ball.getImageHeight()) > item.getPosition()[1]  &&  ball.getPosition()[1] < (item.getPosition()[1]+item.getImageHeight())) {  
			boolean isBallDx = false;
			if (ball.getPosition()[0] > (item.getPosition()[0]+item.getImageWidth()/2)) isBallDx = true;
			if (ball.getPosition()[0] <= (item.getPosition()[0]+item.getImageWidth()+ballSpeed) && (ball.getPosition()[0]+ball.getImageWidth()) >= item.getPosition()[0]-ballSpeed) {
				if (isBallDx) ball.setXdir(1);
				else ball.setXdir(-1);
				item.hit();
				//if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollision(ScreenItem item) {
		this.ballSpeed = ball.getSpeed();
		if ((ball.getPosition()[0]+ball.getImageWidth()) > item.getPosition()[0] && ball.getPosition()[0] < (item.getPosition()[0] + item.getImageWidth())) {
			boolean isBallBelow = false;
			if (ball.getPosition()[1] >= (item.getPosition()[1]+item.getImageHeight()/2)) isBallBelow = true;
			if (ball.getPosition()[1] <= (item.getPosition()[1]+item.getImageHeight()+ballSpeed) && (ball.getPosition()[1]+ball.getImageHeight()) >= item.getPosition()[1]-ballSpeed) {
				if (isBallBelow) ball.setYdir(1);
				else ball.setYdir(-1);
				item.hit();
				//if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollision(Paddle item) {
        this.ballSpeed = ball.getSpeed();
        if ((ball.getPosition()[0]+ball.getImageWidth()) > item.getPosition()[0] && ball.getPosition()[0] < (item.getPosition()[0] + item.getImageWidth())) {
            boolean isBallBelow = false;
            if (ball.getPosition()[1] >= (item.getPosition()[1]+item.getImageHeight()/2)) isBallBelow = true;
            if (ball.getPosition()[1] <= (item.getPosition()[1]+item.getImageHeight()+ballSpeed) && (ball.getPosition()[1]+ball.getImageHeight()) >= item.getPosition()[1]-ballSpeed) {
                if (isBallBelow) ball.setYdir(1);
                else ball.setYdir(-1);
                if (ball.getPosition()[0]+ball.getImageWidth()/2 >= (item.getPosition()[0]+item.getImageWidth()/2)) ball.setXdir(1);
                else ball.setXdir(-1);
                //if (collisionMusic.isMusicOn()) collisionMusic.playMusic(MusicTypes.HIT);
                return true;
            }
        }
        return false;
    }
}
