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
		
		for(int i = 0; i < 4; i++) {//first 2 layers up
			for (int j = 0; j < 2; j++) { 
				
				int[] posInitBrick = new int[2];
				
				// posizione di partenza dei Brick
				posInitBrick[0] = i * 80 + 100;  //nell'asse x
				posInitBrick[1] = j * 43 + 193; //nell'asse y
		
				// creo i Bricks
				objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
			}
		}
		for(int i = 0; i < 4; i++) {//first 2 layers down
			for (int j = 0; j < 2; j++) { 
				
				int[] posInitBrick = new int[2];
				
				// posizione di partenza dei Brick
				posInitBrick[0] = i * 80 + 100;  //nell'asse x
				posInitBrick[1] = j * 45 + 325; //nell'asse y
		
				// creo i Bricks
				objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
			}
		}
		for (int i = 0; i < 1; i++) { //1 left bricks in the middle
			int[] posInitBrick = new int[2];
			posInitBrick[0] = i * 80+ 100;  //nell'asse x
			posInitBrick[1] = 280; //nell'asse y
			objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
		}
		
		for (int i = 0; i < 1; i++) { //1 right bricks in the middle
			int[] posInitBrick = new int[2];
			posInitBrick[0] = i * 80+ 340;  //nell'asse x
			posInitBrick[1] = 280; //nell'asse y
			objBricks.add(new Brick(brick, 65, 25, posInitBrick, 4));
		}
		
		//1 central bricks in the middle
		int[] posInitBrick = new int[2];
		posInitBrick[0] = 210+ 10;  //nell'asse x
		posInitBrick[1] = 280; //nell'asse y
		objBricks.add(new Brick(brick, 60, 25, posInitBrick,4));
		
		int[] posFastBrick = {175,275};//speed special brick
		PowerUp speedUp = new BallSpeedUp(objBall);
		objBricks.add(new Brick(fastBrick, 35, 35, posFastBrick,1, speedUp));
		
		int[] posFlipBrick = {293,275};//change-direction special brick
		PowerUp fakeFlip = new SwitchPaddleDirection();
		objBricks.add(new Brick(flipBrick, 35, 35, posFlipBrick,1, fakeFlip));
		
	
									
		return (ArrayList<Brick>) objBricks;
	}
}
