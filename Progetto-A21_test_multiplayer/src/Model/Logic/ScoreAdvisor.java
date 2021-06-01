package Model.Logic;

import java.awt.List;
import java.util.ArrayList;

import Model.BreakoutGame;
import Model.Core.Screen;

public class ScoreAdvisor {

	//private ArrayList<Player> players;
	private Screen s;
	
	public ScoreAdvisor(Screen s) {
		
		//this.players = (ArrayList) g.getPlayers();
		this.s = s;
		
	}
	
	
	public void addPoint(Player p) {
		
		p.addPoint2Player();
		
		
	}
	
	public void resetPoints(Player p) {
		
		p.resetPoints();
		
		
	}
	
	public int getScore(Player p) {
		
		return p.getPlayerScore();
	}
	
	
	
	
}
