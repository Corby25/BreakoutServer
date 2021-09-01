package Model.Core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.HashMap;

import Model.BreakoutGame;
import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.BrickPowerUp;
import Model.Items.Item;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.PowerUp.PowerUp;
import Model.Logic.CollisionAdvisor;
import Model.Logic.Drawer;
import Model.Logic.LifeAdvisor;
import Model.Logic.Player;
import Model.Logic.PowerUpListComparator;
import Model.Logic.ScreenItemFactory;
import Model.Logic.Levels.Levels;
//import Music.MusicTypes;
import Utility.Utilities;


public class Screen extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	protected BreakoutGame game;
	private boolean gameStatus = false;
	private boolean gameOver = false;
	private boolean gameWin = false;
	private Ball objBall;
	protected ArrayList<Brick> objBricks;
	protected HashMap<PowerUp, ScreenItem> objPowerUp;
	private ArrayList<Paddle> objPaddles;
	private ScreenItem objSfondo, objHit, objBox, objSpeedUpLogo, objSwitchLogo, objLongerLogo, objShorterLogo, objWin, objLose;
	protected ScreenItem[] objLife;
	protected ScreenItem[] objOn;
	boolean isMusicOn;
	private Graphics g;
	CollisionAdvisor advisor;
	private Levels levels;
	private ArrayList<Player> players;
	private LifeAdvisor lifeAdvisor;
	private boolean victory = false;
	private boolean loss = false; 
	private int score;
	private ArrayList<Integer> paddlesPositionsX; 
	private ArrayList<Integer> paddlesPositionsY; 
	protected int lastScore, numberOfPlayers, currentLevel;
	protected Drawer drawer;
	
	public Screen(BreakoutGame game) {
		this.game = game;
		objBricks = new ArrayList<Brick>();
		objPowerUp = new HashMap<PowerUp, ScreenItem>();
		objPaddles = new ArrayList<Paddle>();
		ScreenItem[] objLife = new ScreenItem[Utilities.NUMBER_LIFE];
		players = new ArrayList<Player>();
		drawer = new Drawer();
	}
	
		// ciclo di gioco
				@Override
		synchronized public void run() {
			
			double previous = System.nanoTime(); 
			double delta = 0.0;
			double fps = 100.0;
			double ns = 1e9/fps; // numero di nano sec per fps
			gameStatus = true;
			
			while (gameStatus) {
				double current = System.nanoTime();
				
				double elapsed = current - previous;
				previous = current;
				delta += elapsed;
		
					while (delta >= ns) {
					   update();	
					   delta -= ns;
					}
			}
		}
		/*
		 *  inzializzazione della partita: creo gli oggetti ScreenItem che poi verranno aggiornati e disegnati.
		 */
		synchronized public void setLevel(int lv) {
			currentLevel = lv;
			this.score = 0;
			
			objBall = (Ball)ScreenItemFactory.getInstance().getScreenItem(Item.BALL);
					
			levels = new Levels(objBall, objPaddles);
			levels.setLevel(lv);
			objBricks = levels.getBricksDesposition(lv);
			//levels.setPlayersPosition(numberOfPlayers);
					
			ArrayList<PowerUp> tempList = new ArrayList<PowerUp>();
			for(Brick tempBrick : objBricks) {
				try {
					if(!((BrickPowerUp) tempBrick == null)) {
						tempList.add(((BrickPowerUp)tempBrick).getPowerUp());
					}
				} catch(ClassCastException e) {	}
			}
					
			advisor = new CollisionAdvisor(objBall/*, mainMusic*/);
					
			lifeAdvisor = new LifeAdvisor(/*mainMusic,*/ advisor, objBall);
			objLife = ScreenItemFactory.getInstance().getScreenItem(Item.LIFE, Utilities.NUMBER_LIFE);
					
			objSpeedUpLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SPEED_UP);
			objSwitchLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SWITCH);
			objSfondo = ScreenItemFactory.getInstance().getScreenItem(Item.SFONDO);
			objBox = ScreenItemFactory.getInstance().getScreenItem(Item.BOX);
			objHit = ScreenItemFactory.getInstance().getScreenItem(Item.HIT);
			objWin = ScreenItemFactory.getInstance().getScreenItem(Item.WIN);
			objLose = ScreenItemFactory.getInstance().getScreenItem(Item.LOSE);
			objLongerLogo = ScreenItemFactory.getInstance().getScreenItem(Item.LONG_UP);
			objShorterLogo = ScreenItemFactory.getInstance().getScreenItem(Item.SHORT_UP);
					
			objOn = ScreenItemFactory.getInstance().getScreenItem(Item.ON, tempList.size());
			PowerUpListComparator c = new PowerUpListComparator();
			tempList.sort(c);
			for(int i=0; i < tempList.size(); i++) {
				if(!objPowerUp.containsKey(tempList.get(i))) objPowerUp.put(tempList.get(i), objOn[i]);
			}
		}
		
		/*
		 *  Game cycle: update(), aggiorno il ciclo di gioco.
		 *  Controllo le collisioni e gestisco cambiamenti nel model dovuto ad esse
		 */
		synchronized public void update() {
		    objBall.move();
		    gameOver = lifeAdvisor.checkLife();
		    gameStatus = advisor.checkBorderCollision();
		    
		    paddlesPositionsX = game.getPaddlesPositionsX();
		    paddlesPositionsY = game.getPaddlesPositionsY();
		    
		    for(int i=0; i < objPaddles.size(); i++) {
		    	objPaddles.get(i).setPosition(paddlesPositionsX.get(i), paddlesPositionsY.get(i));
		    	advisor.checkCollisionLato(objPaddles.get(i));
		    	advisor.checkCollision(objPaddles.get(i));
		    }
		    
		    advisor.checkCollisionLato(objBox);
			
			for (Brick tempBrick : objBricks) {
				if (!tempBrick.isDestroyed()) {
					if(advisor.checkCollisionLato(tempBrick) || advisor.checkCollision(tempBrick)) {
						score++;
					}
					if(tempBrick.isDestroyed()) {
						tempBrick.activatePowerUP();
					}
				}
			}
			
			objPaddles.get(0).move();
			
			if(endGame()) {
				victory = true;
			}
			
			if(lifeAdvisor.getLife() < 0) {
				loss = true;
			}
			
			if (objPaddles.size()>1) objPaddles.get(1).move(objBall.getXPosition(), objBall.getYPosition(),objBall.getImageWidth());// bot
		}
				
		public String stringGameFullStatus() {
			
			StringBuilder stringGameFullStatus = new StringBuilder();
			
			for (Paddle tempPaddle : objPaddles) {
				stringGameFullStatus.append(tempPaddle.getXPosition());
				stringGameFullStatus.append(" ");
				stringGameFullStatus.append(tempPaddle.getYPosition());
				stringGameFullStatus.append(" ");
			}
			for (Brick tempBrick : objBricks) {
				stringGameFullStatus.append(tempBrick.getHitLevel());
				stringGameFullStatus.append(" ");
			}
			stringGameFullStatus.append(objBall.getXPosition()+" "+objBall.getYPosition()+" ");
			stringGameFullStatus.append(score+" "+(lifeAdvisor.getLife())+" ");
			for(PowerUp powerUp : objPowerUp.keySet()) {
				stringGameFullStatus.append(Boolean.toString(powerUp.isActive())+" ");
			}
			stringGameFullStatus.append(Boolean.toString(victory)+" "+Boolean.toString(loss));
			for (int i=0; i<players.size(); i++) {
				stringGameFullStatus.append(" "+ players.get(i).toString());
			}


			return stringGameFullStatus.toString();
		}
		
		private boolean endGame() {
			int win = 0;
			for(Brick tempBrick : objBricks) {
				if(tempBrick.isDestroyed()) {
					win++;
				}
			}
			if(win == objBricks.size()) {
				return true;
			} else return false;
		}

		
		private void endGameWin() {
			int n = 0;
			for(Brick tempBrick : objBricks) {
				if(!tempBrick.isDestroyed()) {
					n++;
				}
			}
			if(n==0) {
				gameStatus=false;
				victory = true;
			}
		}
		
		public boolean isGameEnded() {
			if (victory || loss) return true;
			return false;
		}
		
		//Aggiungo player alla partita
		public void addPlayers(ArrayList<Player> players) {
			this.players = players;
			for (Player tempPlayer : players) {
				objPaddles.add(tempPlayer.getObjPaddle());
			}
		}
		
		public Graphics getG() {
			return g;
		}
		
		public ArrayList<Brick> getBricks() {
			return (ArrayList<Brick>) objBricks;
		}
		
		public int[] getBall() {
			return objBall.getPosition();
		}
		
		/*
		 * Setta il numero di giocatori
		 */
		public void setNumberOfPlayers(int n) {
			
			this.numberOfPlayers = n;
		}
	}