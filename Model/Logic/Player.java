package Model.Logic;

import java.awt.image.BufferedImage;

import Model.Items.Paddle;
import Utility.Utilities;

public class Player {

	private String name;
	private int numberPlayer;
	private int idPlayer;
	private Paddle objPaddle;
	private BufferedImage paddle;
	//private ImagesLoader loader;
	private InputAdapter inputHandler;
	//private int playerScore;
	//private int life;

	public Player(String name) {
		
		this.name = name;
		this.numberPlayer = numberPlayer;
		this.idPlayer = idPlayer;
		inizialize();
		this.inputHandler = new InputAdapter(objPaddle);
		//this.life = 3;
	}
	
	public void inizialize() {
		
		// posizione di partenza paddle
		int[] posInitPaddle = new int[2];
		posInitPaddle[0] = Utilities.INITIAL_POSITION_PADDLE_X;  // x
		posInitPaddle[1] = Utilities.INITIAL_POSITION_PADDLE_Y;  // y
					
		// creo un paddle 
		objPaddle = new Paddle(100, 30, posInitPaddle);

		//this.playerScore = 0;
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
	
	public String toString() {
		return name;
	}
	
	/*
	public void setLimit() {
		if(idPlayer)
	}
	*/
}
