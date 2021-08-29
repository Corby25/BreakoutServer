package GUI.menu.Graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import Utility.Utilities;


public class GameFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GameFrame() {
		
		setTitle("Breakout");
		Dimension dimension_screen = new Dimension(Utilities.SCREEN_WIDTH,Utilities.SCREEN_HEIGHT);
		setPreferredSize(dimension_screen);
		setResizable(true);
		inzialize();
		
	}
	
	public void inzialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
}
