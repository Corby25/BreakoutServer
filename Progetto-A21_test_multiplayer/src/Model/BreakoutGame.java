package Model;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import GUI.menu.Graphics.GameFrame;
import Model.Core.Levels;
import Model.Core.Screen;
import Model.Core.TypeLevels;
import Model.Items.Utilities;
import Model.Logic.Player;
import Model.Logic.ScoreAdvisor;

public class BreakoutGame {
	
	// controller tra la logica e la gui
	
	private GameFrame gameFrame; //creazione nuova finestra
	private Screen screen; 
	private List<Player> players; // definizione dei giocatori
	private List<InetAddress> addresses;
	private List<Integer> ports; 
	private List<Integer> localPorts; 
	private List<ServerThread> serverThreads;
	private Thread gameThread, gameThread2; // thread di gioco
	private Boolean music; // setup musica
	private Player p; 
	private ScoreAdvisor score;
	private Server server;
	private TypeLevels lv;
	private int numberOfPlayers;
	
	// creazione del controller
	public BreakoutGame(int numberOfPlayers) {
		addresses=new ArrayList<>();
		ports=new ArrayList<>();
		localPorts=new ArrayList<>();
		serverThreads=new ArrayList<>();
		this.gameFrame = new GameFrame();
		players = new ArrayList<Player>();
		this.lv = TypeLevels.LEVEL1;
		this.numberOfPlayers = numberOfPlayers;
	}

	// avvio menu principale e creazione gioco
	public void start() {	
		// creazione gioco 
		this.screen = new Screen(this);
		for (int i=0; i<numberOfPlayers;i++) {
			DatagramSocket newDatagramSocket;
			try {
				newDatagramSocket = new DatagramSocket(localPorts.get(i));
				serverThreads.add(new ServerThread(newDatagramSocket,addresses.get(i), ports.get(i), screen));
				serverThreads.get(i).start();
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.score = new ScoreAdvisor(screen); 
		gameSetup();
		
	}

	
	// inizializzazione gioco e giocatori a sceonda delle scelte dell'utente
	public void gameSetup() {
		
	
		p=players.get(0);
		// creo un giocatore
		players.add(p);
		
		screen.newPlayer(p);
		
		screen.start();
		
		screen.setLevel(lv);
		
		gameFrame.add(screen);
		gameFrame.requestFocusInWindow();

		// aggiungo controllo da tastiera
		gameFrame.addKeyListener(p.getInputHandler());
		gameFrame.pack();
		gameFrame.setVisible(true);
				
		// avvio ciclo di gioco
		new Thread(screen).start();
		screen.setVisible(true);
	}

	public ScoreAdvisor getScoreAdvisor() {
		
		return score;
	}
	
	
	public List<Player> getPlayers() {		
		return players;
	}

	public boolean addPlayer(Player p, InetAddress address, int port, int newLocalPort) {
		if (players.size()<numberOfPlayers) {
			players.add(p);
			addresses.add(address);
			ports.add(port);
			localPorts.add(newLocalPort);
			if (players.size() == numberOfPlayers) start();
			return true;
		}
		else return false;
	}
	
	public ArrayList getPaddlesPositions() {
		ArrayList<Integer> paddlesPositions = new ArrayList<>();
	
		for (int i=0; i<numberOfPlayers;i++) {
			paddlesPositions.add(serverThreads.get(i).getPaddlePoisition());
		}
		return paddlesPositions;
	}
	
	
	
}
