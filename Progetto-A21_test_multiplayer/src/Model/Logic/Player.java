package Model.Logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import GUI.ImagesLoader;
import Model.Items.Paddle;
import Model.Items.Utilities;

public class Player {

	private Paddle objPaddle;
	private BufferedImage paddle;
	private ImagesLoader loader;
	private InputAdapter inputHandler;
	private int playerScore;
	private int life;
	private String name;

	public Player(String name) {
		this.name= name;
		createImage();
		inizialize();
		this.inputHandler = new InputAdapter(objPaddle);
		this.life = 3;
	}
	
	public void inizialize() {
		
		// posizione di partenza paddle
		int[] posInitPaddle = new int[2];
		posInitPaddle[0] = Utilities.INITIAL_POSITION_PADDLE_X;  // x
		posInitPaddle[1] = Utilities.INITIAL_POSITION_PADDLE_Y;  // y
					
		// creo un paddle 
		objPaddle = new Paddle(paddle, 100, 30, posInitPaddle);

		this.playerScore = 0;
	}
	
	public void createImage() {
		
		this.loader = new ImagesLoader();
		this.paddle = loader.uploadImage("/Images/paddle.png");

	}
	
	public void move() {
		objPaddle.moveLeft();
	}

	public InputAdapter getInputHandler() {
		return inputHandler;
	}

	public void setInputHandler(InputAdapter inputHandler) {
		this.inputHandler = inputHandler;
	}
	

	public Paddle getObjPaddle() {	
		return objPaddle;
	}
	

	

	

}
