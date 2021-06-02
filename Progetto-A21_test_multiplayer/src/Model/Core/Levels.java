package Model.Core;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.Paddle;
import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.SwitchPaddleDirection;

public class Levels {
	private TypeLevels level;
	private List<Brick> objBricks;
	BufferedImage brick, fastBrick, flipBrick;
	private Ball objBall;
	
	public Levels(BufferedImage brick, BufferedImage fastBrick,BufferedImage flipBrick, Ball objBall) {
		this.level = TypeLevels.LEVEL1;
		this.brick = brick;
		this.fastBrick = fastBrick;
		this.flipBrick = flipBrick;	
		this.objBall = objBall;
		objBricks = new ArrayList<Brick>();
		}
	
	public void setLevel(TypeLevels level) {
		this.level = level;
	}
	
	public ArrayList<Brick> getBricksDesposition() {
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) { 
				
				int[] posInitBrick = new int[2];

				// posizione di partenza dei Brick
				posInitBrick[0] = i * 110 + 50;  //nell'asse x
				posInitBrick[1] = j * 60 + 150; //nell'asse y
		
				// creo i Bricks
				objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
			}
		}
		
		for (int i = 0; i < 3; i++) {
			int[] posInitBrick = new int[2];
			posInitBrick[0] = i * 165 + 50;  //nell'asse x
			posInitBrick[1] = 90; //nell'asse y
			objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
		}
		
		for (int i = 0; i < 4; i++) {
			int[] posInitBrick = new int[2];
			posInitBrick[0] = i * 110 + 50;  //nell'asse x
			posInitBrick[1] = 30; //nell'asse y
			objBricks.add(new Brick(brick, 65, 25, posInitBrick,4));
		}
		
		/*int[] posFastBrick = {150,85};
		PowerUp speedUp = new BallSpeedUp(objBall);
		objBricks.add(new Brick(fastBrick, 35, 35, posFastBrick,1, speedUp));*/
		
		int[] posFlipBrick = {315,85};
		objBricks.add(new Brick(flipBrick, 35, 35, posFlipBrick,1));
									
		return (ArrayList<Brick>) objBricks;
	}
}
