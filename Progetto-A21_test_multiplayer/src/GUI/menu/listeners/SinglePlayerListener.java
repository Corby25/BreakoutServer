package GUI.menu.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import Model.BreakoutGame;


public class SinglePlayerListener extends Listener implements ActionListener{

	
	public SinglePlayerListener(BreakoutGame game, JPanel m) {
		super(game, m);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		removeOldPanel();
		game.gameSetup();
		
	}



	
	
}
