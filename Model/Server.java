package Model;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Core.Screen;
import Model.Logic.Player;



public class Server {
	
    private ArrayList<ServerThread> threadsIn;
    private HashMap<String,BreakoutGame> games;
    private ArrayList<BreakoutGame> randomGames;
    BreakoutGame gameToJoin;
    static int serverPort = 2001;
    static int nextAvailablePort = serverPort+1;
    
    public Server() {
    	games = new HashMap<>();
    	randomGames = new ArrayList<>();
    }
       
    public void initServer() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(serverPort);
            while (true) {
                byte[] b = new byte[1024];
                DatagramPacket packet = new DatagramPacket(b, b.length);
                datagramSocket.receive(packet);
                String AllInfo = new String(packet.getData(), 0, packet.getLength());
                String AllInfos[] = AllInfo.split(" ");
                String playerName = AllInfos[1];
        		String gameCode = AllInfos[2];
                int numberOfPlayers = Integer.parseInt(AllInfos[3]);
                int level = 1;

        		System.out.println(AllInfo);

            	if (gameCode.equals("RANDOM")) {
            		if (randomGames.size()==0) {
	                	Server.nextAvailablePort += 1;
            			randomGames.add(new BreakoutGame(numberOfPlayers, datagramSocket));
            			randomGames.get(0).addPlayer(new Player(playerName), packet.getAddress(), packet.getPort(), Server.nextAvailablePort);
            			gameToJoin=randomGames.get(0);
            		}
            		else {
	                	Server.nextAvailablePort += 1;
            			boolean foundGame = false;
            			for (BreakoutGame tempGame :  randomGames) {
            				if (foundGame) break;
            				if (tempGame.getNumberOfPlayers() == numberOfPlayers & tempGame.getNumMissingPlayers() != 0) {
            					tempGame.addPlayer(new Player(playerName), packet.getAddress(), packet.getPort(), Server.nextAvailablePort);
            					gameToJoin = tempGame;
            					foundGame = true;
            				}
            			}
            			if (!foundGame) {
            				randomGames.add(new BreakoutGame(numberOfPlayers, datagramSocket));
                			randomGames.get(randomGames.size()-1).addPlayer(new Player(playerName), packet.getAddress(), packet.getPort(), Server.nextAvailablePort);
                			gameToJoin = randomGames.get(randomGames.size()-1);
            			}
            		}
            		b = ("true "+Server.nextAvailablePort+" "+gameToJoin.getNumMissingPlayers()+" "+numberOfPlayers+" "+gameToJoin.getLastPlayerIndex() + " " + gameToJoin.getNumberLevel()).getBytes();
                    DatagramPacket packetBack = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());
                    datagramSocket.send(packetBack);
            	}
            	else {
	            	if (AllInfos[0].equals("true")) {
	                	Server.nextAvailablePort += 1;
	                	int numberOfMissingPlayers = 0;
	                	boolean gameExists= games.containsKey(gameCode);
	                    int playerIndex = 0;
	                	if (!gameExists) {
		                	games.put(gameCode, new BreakoutGame(numberOfPlayers, datagramSocket)); 
		                	gameToJoin = games.get(gameCode);
		                	gameToJoin.addPlayer(new Player(playerName), packet.getAddress(), packet.getPort(), Server.nextAvailablePort);
		                	numberOfMissingPlayers = gameToJoin.getNumMissingPlayers();
		                	playerIndex = gameToJoin.getLastPlayerIndex();
		                	level = gameToJoin.getNumberLevel();
	                	}
		                b = (Boolean.toString(!gameExists)+" "+Server.nextAvailablePort+" "+numberOfMissingPlayers+" "+numberOfPlayers+" "+playerIndex+" "+ level).getBytes();
	                    DatagramPacket packetBack = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());
	                    datagramSocket.send(packetBack);
	                }
	                else {
	                	Server.nextAvailablePort += 1;
	                	Integer numberOfMissingPlayers = 0;
	                	boolean joinSuccess = false;
	                	boolean gameExists= games.containsKey(gameCode);
	                    int playerIndex = 0;
	                	if (gameExists) {
	                		joinSuccess = true;
		                    gameToJoin = games.get(gameCode);
		                    boolean isAdded = gameToJoin.addPlayer(new Player(playerName), packet.getAddress(), packet.getPort(), Server.nextAvailablePort);
		                	numberOfMissingPlayers = gameToJoin.getNumMissingPlayers();
		                	playerIndex = gameToJoin.getLastPlayerIndex();
		                	numberOfPlayers = gameToJoin.getNumberOfPlayers();
		                	level = gameToJoin.getNumberLevel();
		                    if (!isAdded) joinSuccess = false;
	                	}
	                
	                    b = (Boolean.toString(joinSuccess)+" "+Server.nextAvailablePort+" "+numberOfMissingPlayers+" "+numberOfPlayers+" "+playerIndex + " "+ level).getBytes();
	                    DatagramPacket packetBack = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());
	                    datagramSocket.send(packetBack);                	
	                }
            	}
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
