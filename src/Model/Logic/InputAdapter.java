package Model.Logic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Model.Items.Paddle;

public class InputAdapter extends KeyAdapter {
	
	private Paddle objPaddle;
	
	public InputAdapter(Paddle paddle) {
		
		this.objPaddle = paddle;
		
	}
	 @Override
     public void keyReleased(KeyEvent e) {

		 objPaddle.keyReleased(e);
     }

     @Override
     public void keyPressed(KeyEvent e) {

    	 objPaddle.keyPressed(e);
     }

}
