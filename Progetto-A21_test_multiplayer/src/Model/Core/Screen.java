package Model.Core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.management.timer.Timer;
import javax.sound.sampled.Clip;


import GUI.ImagesLoader;
import Model.BreakoutGame;
import Model.Server;
import Model.Items.Ball;
import Model.Items.Box;
import Model.Items.Brick;
import Model.Items.Paddle;
import Model.Items.ScreenItem;
import Model.Items.Utilities;
import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.PowerUpTypes;
import Model.Items.PowerUp.SwitchPaddleDirection;
import Model.Logic.CollisionAdvisor;
import Model.Logic.LifeAdvisor;
import Model.Logic.Player;


public class Screen extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage box, ball, brick, brick1, brick2, brick3, fastBrick, hitBox, flipBrick, sfondo, youWin, youLose, on, off, fastLogo, flipLogo, life;
	//SpecialBrick objFlip, objFast;
	private boolean gameStatus = false;
	private boolean gameOver = false;
	private boolean gameWin = false;
	private boolean isFastStarted = false;
	private boolean isFlipStarted = false;
	private boolean isFastActive = false;
	private boolean isFlipActive = false;
	private Ball objBall;
	private List<Brick> objBricks;
	private Box objBox;
	//private List<SpecialBrick> objSpecialBricks;
	private ScreenItem objSfondo;
	private ImagesLoader loader;
	private List<Paddle> objPaddles;
	private ArrayList<Integer> paddles;
	Clip win,hit;
	boolean isMusicOn;
	private Graphics g;
	CollisionAdvisor ball1;
	private BreakoutGame game;
	private Levels levels;
	private ArrayList<Player> players;
	double fastStartTime = 0;
	double switchStart = 0;
	private LifeAdvisor lifePlayer;
	double flipStartTime = 0;
	private boolean victory;
	private boolean loss;
	private int score;
	private Server server;
	private ArrayList<Integer> paddlesPositionsY;
	private int fastRemainingTime, flipRemainingTime;

	
	public Screen(BreakoutGame game) {
		this.game = game;
		objBricks = new ArrayList<Brick>();
		objPaddles = new ArrayList<Paddle>();
		victory = false;
		loss = false;

		//objSpecialBricks = new ArrayList<SpecialBrick>();
		uploadImages();
	}
	
	
	private ArrayList<Integer> paddlesPositionsX;


	// caricamento immagini 
		private void uploadImages() {
			
			loader = new ImagesLoader();
			box = loader.uploadImage("../Images/box.png");
			hitBox = loader.uploadImage("../Images/hit.png");
			ball = loader.uploadImage("../Images/ball.png");
			sfondo = loader.uploadImage("/Images/sfondo.jpeg");
			brick = loader.uploadImage("/Images/brick.png");
			brick1 = loader.uploadImage("/Images/brick1.png");
			brick2 = loader.uploadImage("/Images/brick2.png");
			brick3 = loader.uploadImage("/Images/brick3.png");
			fastBrick = loader.uploadImage("/Images/fast.png");
			flipBrick = loader.uploadImage("/Images/flip.png");
			youWin = loader.uploadImage("/Images/youWin.png");
			youLose = loader.uploadImage("/Images/lose.png");
			on = loader.uploadImage("/Images/on.png");
			off = loader.uploadImage("/Images/off.png");
			fastLogo = loader.uploadImage("/Images/fastLogo.png");
			flipLogo = loader.uploadImage("/Images/flipLogo.png");
			life = loader.uploadImage("/Images/life.png");
		}
		
		// ciclo di gioco
				@Override
		public void run() {
			
			double previous = System.nanoTime(); 
			double delta = 0.0;
			double fps = 100.0;
			double ns = 1e9/fps; // numero di nano sec per fps
			gameStatus = true;
			
			//switchare off/on
			//if (mainMusic.isMusicOn()) mainMusic.playMusic(MusicTypes.LOOP);
			
			while (gameStatus) {
				double current = System.nanoTime();
				
				double elapsed = current - previous;
				previous = current;
				delta += elapsed;
		
					while (delta >= ns) {
					   update();	
					   delta -= ns;
					}
				render();
			}
		}

		// disegno di oggetti grafici a schermo
		synchronized public void render() {
			
			// creazione di 2 buffer cos� che l'immagine venga aggiornata su uno e mostrata sull'altro 
			// modo ciclico, evita gli scatti.
			
			BufferStrategy buffer = this.getBufferStrategy();
			
			if(buffer == null) {
				createBufferStrategy(2);
				return;	
			}
			
			this.g = buffer.getDrawGraphics();// oggetto di tipo Canvas su cui si pu� disegnare
			
			g.setFont(new Font("Courier", Font.BOLD, 25)); 
			g.setColor(Color.WHITE);
			
			objSfondo.render(g, this);
			objBall.render(g);
		    for (Paddle tempPaddle : objPaddles) tempPaddle.render(g);
			objBox.render(g);
            g.drawImage(hitBox, 508, 3, 30, 30, null);
            
            g.drawImage(fastLogo, 508, 120, 25, 25, null);
            if(isFastActive) {
            	if (System.nanoTime() >= fastStartTime+6e9) 
            		g.drawString(""+(int)((fastStartTime+10e9-System.nanoTime())/1e9), 510, 170);
            	else g.drawImage(on, 508, 153, 25, 25, null);
            }
            else g.drawImage(off, 508, 153, 25, 25, null);
            
            g.drawImage(flipLogo, 508, 195, 25, 25, null);
            if(isFlipActive) {
            	if (System.nanoTime() >= flipStartTime+6e9) 
            		g.drawString(""+(int)((flipStartTime+10e9-System.nanoTime())/1e9), 510, 245);
            	else g.drawImage(on, 508, 228, 25, 25, null);
            }
            else g.drawImage(off, 508, 228, 25, 25, null);
		
			g.drawString(((Integer) score).toString(), 505, 58);
			
			int n = 0;
			for (Brick tempBrick : objBricks) {
				if (!tempBrick.isDestroyed()) {
					n++;
					if(!tempBrick.getHasPowerUp()) {
						int hitLevel = tempBrick.getHitLevel();
						switch (hitLevel) {
							case 1:
								tempBrick.setImage(brick3); 
								break;
							case 2:
								tempBrick.setImage(brick2); 
								break;
							case 3:
								tempBrick.setImage(brick1); 
								break;
						    default:
						    	tempBrick.setImage(brick);
					        }
					}
					tempBrick.render(g);
				}
			}
			if(n==0) {
				g.drawImage(youWin, 495/2-150, Utilities.SCREEN_HEIGHT/2 - 100, 300, 70, null);
				gameWin = true; 
			}
			
			switch (players.get(0).getLife()) {
				case 1:
					g.drawImage(life, 505, 78, 20, 20, null); 
					break;
				case 2:
					g.drawImage(life, 505, 78, 20, 20, null); 
					g.drawImage(life, 505, 88, 20, 20, null); 
					break;
				case 3:
					g.drawImage(life, 505, 78, 20, 20, null); 
					g.drawImage(life, 505, 88, 20, 20, null); 
					g.drawImage(life, 505, 98, 20, 20, null);
			}

		    //endGameOver();
			
			if (gameOver) g.drawImage(youLose, 495/2 - 250, Utilities.SCREEN_HEIGHT/2 - 250, 500, 500, null);
			
			g.dispose();
			
			buffer.show();
		}
		
		// aggiornamento ciclo di gioco
		synchronized public void update() {
			
			endGameWin();
			endGameOver();
			
		    objBall.move();
		    gameOver = lifePlayer.checkLife();
		    gameStatus = ball1.checkBorderCollision();
			ball1.checkCollisionLato(objBox);
		    
		    paddlesPositionsX = game.getPaddlesPositionsX();
		    paddlesPositionsY = game.getPaddlesPositionsY();
		    for (int i=0; i<objPaddles.size(); i++) {
		    	objPaddles.get(i).setPosition(paddlesPositionsX.get(i), paddlesPositionsY.get(i));
				ball1.checkCollisionLato(objPaddles.get(i));
				ball1.checkCollision(objPaddles.get(i));
		    }
			
			for (Brick tempBrick : objBricks) {
				if (!tempBrick.isDestroyed()) {
					if(ball1.checkCollisionLato(tempBrick) || ball1.checkCollision(tempBrick)) {
						score += 1;	
					}
					if(tempBrick.isDestroyed()) {
						if (tempBrick.whichPower() == PowerUpTypes.FAST) isFastStarted = tempBrick.activatePowerUP();
						if (tempBrick.whichPower() == PowerUpTypes.FLIP) isFlipStarted = true;
					}
					if(isFastStarted) {
						fastStartTime = System.nanoTime();
						isFastActive = true;
						isFastStarted = false;
					}
					if(isFlipStarted) {
						flipStartTime = System.nanoTime();
						isFlipActive = true;
						isFlipStarted = false;
					}
				}
				if (System.nanoTime() >= fastStartTime+10e9 && tempBrick.whichPower() == PowerUpTypes.FAST) {
					isFastActive = false;
					tempBrick.disactivatePowerUp();
				}
				if (System.nanoTime() >= flipStartTime+10e9 && tempBrick.whichPower() == PowerUpTypes.FLIP) {
					isFlipActive = false;
				}
			}
			fastRemainingTime = (int)((flipStartTime+10e9-System.nanoTime())/1e9);
			flipRemainingTime = (int)((fastStartTime+10e9-System.nanoTime())/1e9);		
		}
		

		// inzializzazione partita
		public void start() {
						
			// posizione di partenza dello sfondo
			int[] posInitSfondo = new int[2];
			posInitSfondo[0] = 0;
			posInitSfondo[1] = 0;
			
			// creo lo sfondo
			objSfondo = new ScreenItem(sfondo, Utilities.SCREEN_WIDTH, Utilities.SCREEN_HEIGHT, posInitSfondo);
			
			int[] posBox = new int[2];
			posBox[0] = 495;  //nell'asse x
			posBox[1] = 0; //nell'asse y
			objBox = new Box(box, 80, 700, posBox);
			
			// posizione di partenza ball
			int[] posInitBall = new int[2];

			posInitBall[0] = Utilities.INTIAL_POSITION_BALL_X;  // x
			posInitBall[1] = Utilities.INITIAL_POSITION_BALL_Y;  // y
			
			// faccio partire il thread corrispondente a ball
			objBall = new Ball(ball, 20, 20, posInitBall);
			
			ball1 = new CollisionAdvisor(objBall);
			
			//creazione e posizionamento dei Bricks
			levels = new Levels(brick, fastBrick, flipBrick, objBall);
			objBricks = levels.getBricksDesposition();

			this.lifePlayer = new LifeAdvisor(players.get(0), ball1, objBall);
			
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
			stringGameFullStatus.append(((Integer) score).toString()+" "+3+" "+Boolean.toString(isFastActive)+" "+fastRemainingTime+" "+Boolean.toString(isFlipActive)+" "+flipRemainingTime+" ");
			stringGameFullStatus.append(Boolean.toString(victory)+" "+Boolean.toString(loss));

			return stringGameFullStatus.toString();
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
	
		private void endGameOver() {
			/*if (life == 0) {
				gameStatus = false;
				loss=true;
			}*/
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
	}