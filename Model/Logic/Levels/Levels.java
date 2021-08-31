package Model.Logic.Levels;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Model.Items.Ball;
import Model.Items.Brick;
import Model.Items.BrickPowerUp;
import Model.Items.Paddle;
import Model.Items.PowerUp.BallSpeedUp;
import Model.Items.PowerUp.LongerPaddle;
import Model.Items.PowerUp.PowerUp;
import Model.Items.PowerUp.ShorterPaddle;
import Model.Items.PowerUp.SwitchPaddleDirection;
import Utility.Utilities;

public class Levels {
	private List<Brick> objBricks;
	private Ball objBall;
	private ArrayList<Paddle> objPaddles;
	private HashMap<Integer, List<Brick>> levels;
	private int nLevel, nLine;
	
	public Levels(Ball objBall, ArrayList<Paddle> objPaddles) {
		this.objBall = objBall;
		this.objPaddles = objPaddles;
		objBricks = new ArrayList<Brick>();
		this.levels = new HashMap<Integer, List<Brick>>();
		this.nLevel = 0;
		readFile();
		getLevels();
		}

	public void setLevel(int level) {
		this.nLevel = level;
	}

	
	private Scanner myReader;
	private int i;

	public void readFile() {
		
		 File levelsFile = new File("./Model/Logic/Levels/levels.txt");
	     try {
			this.myReader = new Scanner(levelsFile);
			System.out.println("LIVELLO CARICATO CORRETTAMENTE!");
		} catch (FileNotFoundException e) {
			System.err.println("LATTURA FILE LEVELS FALLITA!");
			e.printStackTrace();
		}
	     
	}
	
	public void getLevels() {
		
		 while (myReader.hasNextLine()) {
			 	String line = myReader.nextLine();
			 	
			    
			    String[] El = line.split(" ");
			    if(El[0].equals("#")) continue;

			    levelCreator(line, nLine++);
			    
			   }
	}
	
	
	public void levelCreator(String line, int nLine) {
		
		String[] l = line.split(" ");
		
		if(l[0].equals("$")) {
			
			nLevel = Integer.parseInt(l[1]);
			this.nLine = 0;
			return;
		}
		
		if(l[0].equals("x")) {
			addLevel(nLevel, new ArrayList<Brick>(objBricks)/*, new ArrayList<PowerUp>(objPowerUps)*/);
			objBricks.clear();
		}
		
		else {
			
		String[] li = line.split("");
		
			for(int j = 0; j < li.length; j++) {
				
				int[] posInitBrick = new int[2];
				// posizione di partenza dei Brick
				posInitBrick[0] = j * 80 + 15;  //nell'asse x
				posInitBrick[1] = nLine * 47 + 129; //nell'asse y
				if(li[j].equals("b")) objBricks.add(new Brick(Utilities.BRICK_WIDTH, Utilities.BRICK_HEIGHT, posInitBrick));
				if(li[j].equals("1")) {
					PowerUp speedUp = new BallSpeedUp(objBall);
					objBricks.add(new BrickPowerUp(Utilities.P_UP_BRICK_WIDTH, Utilities.P_UP_BRICK_HEIGHT, posInitBrick, speedUp));
				}
				if(li[j].equals("2")) {
					PowerUp flipUp = new SwitchPaddleDirection(objPaddles.get(0));
					objBricks.add(new BrickPowerUp(Utilities.P_UP_BRICK_WIDTH, Utilities.P_UP_BRICK_HEIGHT, posInitBrick, flipUp));
				}
				if(li[j].equals("3")) {
					PowerUp longUp = new LongerPaddle(objPaddles);
					objBricks.add(new BrickPowerUp(Utilities.P_UP_BRICK_WIDTH, Utilities.P_UP_BRICK_HEIGHT, posInitBrick, longUp));
					}
				if(li[j].equals("4")) {
					PowerUp shortUp = new ShorterPaddle(objPaddles);
					objBricks.add(new BrickPowerUp(Utilities.P_UP_BRICK_WIDTH, Utilities.P_UP_BRICK_HEIGHT, posInitBrick, shortUp));
					}
				}
			}	
		}
	
		
	
	
	public void addLevel(int nLevel, List<Brick> items/*, List<PowerUp> pUps*/) {
		
		levels.put(nLevel, items);
		//levelUp.put(nLevel, pUps);
		
	}
	
	public int getNumberOfLevels() {
		
		return nLevel;
	}
	
	public ArrayList<Brick> getBricksDesposition(int lv) {
		return (ArrayList<Brick>) levels.get(lv);
	}

	public int getActualLevel() {
		
		return this.nLevel;
	}
}

