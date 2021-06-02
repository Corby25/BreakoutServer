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
    BreakoutGame gameToJoin;
    static int serverPort = 4765;
    static int nextAvailablePort = serverPort+1;
    
    public Server() {
    	games = new HashMap<>();
    }
       
    public void initServer() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(serverPort);
            /*threadsIn = new ArrayList<ServerThread>();
            ArrayList<DatagramSocket> sockets = new ArrayList<DatagramSocket>();*/
            while (true) {
                byte[] b = new byte[1024];
                DatagramPacket packet = new DatagramPacket(b, b.length);
                datagramSocket.receive(packet);
                String AllInfo = new String(packet.getData(), 0, packet.getLength());
                String AllInfos[] = AllInfo.split(" ");
                String playerName = AllInfos[1];
        		String gameCode = AllInfos[2];
        		System.out.println(AllInfos[0]);
        		System.out.println(playerName);
            	System.out.println(gameCode);
            	System.out.println(AllInfos[3]);

            	
            	
            	if (AllInfos[0].equals("true")) {
                	Server.nextAvailablePort += 1;
                	Integer numberOfMissingPlayers = 0;
                	boolean gameExists= games.containsKey(gameCode);
                    int numberOfPlayers = Integer.parseInt(AllInfos[3]);
                    int playerIndex = 0;
                	if (!gameExists) {
	                	games.put(gameCode, new BreakoutGame(numberOfPlayers, datagramSocket)); 
	                	gameToJoin = games.get(gameCode);
	                	gameToJoin.addPlayer(new Player(playerName), packet.getAddress(), packet.getPort(), Server.nextAvailablePort);
	                	numberOfMissingPlayers = gameToJoin.getNumMissingPlayers();
	                	playerIndex = gameToJoin.getLastPlayerIndex();
                	}
	                b = (Boolean.toString(!gameExists)+" "+Server.nextAvailablePort+" "+numberOfMissingPlayers.toString()+" "+numberOfPlayers+" "+playerIndex).getBytes();
                    DatagramPacket packetBack = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());
                    datagramSocket.send(packetBack);
                }
                else {
                	Server.nextAvailablePort += 1;
                	Integer numberOfMissingPlayers = 0;
                	boolean joinSuccess = false;
                	boolean gameExists= games.containsKey(gameCode);
                    int playerIndex = 0;
                    int numberOfPlayers = 0;
                	if (gameExists) {
                		joinSuccess = true;
	                    gameToJoin = games.get(gameCode);
	                    boolean isAdded = gameToJoin.addPlayer(new Player(playerName), packet.getAddress(), packet.getPort(), Server.nextAvailablePort);
	                	numberOfMissingPlayers = gameToJoin.getNumMissingPlayers();
	                	playerIndex = gameToJoin.getLastPlayerIndex();
	                	numberOfPlayers = gameToJoin.getNumberOfPlayers();
	                    if (!isAdded) joinSuccess = false;
                	}
                
                    b = (Boolean.toString(joinSuccess)+" "+Server.nextAvailablePort+" "+numberOfMissingPlayers.toString()+" "+numberOfPlayers+" "+playerIndex).getBytes();
                    DatagramPacket packetBack = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());
                    datagramSocket.send(packetBack);                	
                }
                
                /*int newPort = portaServer + threadsIn.size() + 1;
                b = ((Integer) newPort).toString().getBytes();
                DatagramPacket packetBack = new DatagramPacket(b, b.length, packet.getAddress(), packet.getPort());
                datagramSocket.send(packetBack);
                sockets.add(new DatagramSocket(newPort));
                names = playerName;
                threadsIn.add(new ServerThread(sockets.get(sockets.size() - 1), packet.getAddress(), packet.getPort(), screen));*/
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    

            /*datagramSocket.close();

            for (ServerThread t : threadsIn) {
                t.start();
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;*/
    
    
    /*public int getPositionClientPaddle (int idClient) {
    	return threadsIn.get(idClient).getPaddlePoisition();
    }*/

}
//ciao
