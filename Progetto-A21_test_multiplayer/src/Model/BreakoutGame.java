package Model;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.DatagramPacket;
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

public class BreakoutGame {
	
	// controller tra la logica e la gui
	
	private GameFrame gameFrame; //creazione nuova finestra
	private Screen screen; 
	private ArrayList<Player> players; // definizione dei giocatori
	private List<InetAddress> addresses;
	private List<Integer> ports; 
	private List<Integer> localPorts; 
	private List<ServerThread> serverThreads;
	private Thread gameThread, gameThread2; // thread di gioco
	private Boolean music; // setup musica
	private Player p; 
	private Server server;
	private TypeLevels lv;
	private int numberOfPlayers;
	private DatagramSocket mainSocket;
	
	// creazione del controller
	public BreakoutGame(int numberOfPlayers, DatagramSocket mainSocket) {
		this.mainSocket = mainSocket;
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
				
		screen.addPlayers(players);
		screen.start();
		
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
			
		gameFrame.add(screen);
		gameFrame.requestFocusInWindow();

		// aggiungo controllo da tastiera
		gameFrame.pack();
		gameFrame.setVisible(true);
				
		// avvio ciclo di gioco
		new Thread(screen).start();
		screen.setVisible(true);
	}

	public boolean addPlayer(Player p, InetAddress address, int port, int newLocalPort) {
		if (players.size()<numberOfPlayers) {
			players.add(p);
			addresses.add(address);
			ports.add(port);
			localPorts.add(newLocalPort);
			sendMissingPlayers();
			if (players.size() == numberOfPlayers) start();
			return true;
		}
		else return false;
	}
	
	public Integer getNumMissingPlayers() {
		Integer numbMissingPlayers = numberOfPlayers-players.size();
		return numbMissingPlayers;
	}
	
	public int getLastPlayerIndex() {
		return players.size();
	}
	
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public void sendMissingPlayers() {
		int numbMissingPlayers = numberOfPlayers-players.size();
		for (int i=0; i<players.size()-1; i++) {
            byte[] b = new byte[1024];
			b = ((Integer)numbMissingPlayers).toString().getBytes();
            DatagramPacket packetBack = new DatagramPacket(b, b.length, addresses.get(i), ports.get(i));
            try {
				mainSocket.send(packetBack);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Integer> getPaddlesPositionsX() {
		ArrayList<Integer> paddlesPositionsX = new ArrayList<>();
	
		for (int i=0; i<numberOfPlayers;i++) {
			paddlesPositionsX.add(serverThreads.get(i).getPaddlePoisitionX());
		}
		return paddlesPositionsX;
	}
	
	public ArrayList<Integer> getPaddlesPositionsY() {
		ArrayList<Integer> paddlesPositionsY = new ArrayList<>();
	
		for (int i=0; i<numberOfPlayers;i++) {
			paddlesPositionsY.add(serverThreads.get(i).getPaddlePoisitionY());
		}
		return paddlesPositionsY;
	}
	
	
	
}
