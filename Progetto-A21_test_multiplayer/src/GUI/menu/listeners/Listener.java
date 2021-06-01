package GUI.menu.listeners;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import GUI.menu.Graphics.GameFrame;
import Model.BreakoutGame;

public abstract class Listener implements ActionListener {

	protected BreakoutGame game;
	protected JPanel m;
	
	public Listener(BreakoutGame game, JPanel m) {
		
		this.game = game;
		this.m = m;
		
	}
	
	public  void removeOldPanel() {
		
		m.removeAll();
		m.setVisible(false);
	}
	
}
