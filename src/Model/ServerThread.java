package Model;

import java.io.EOFException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Model.Core.Screen;

public class ServerThread extends Thread {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
	private int paddlePoisitionX = 0;
	private int paddlePoisitionY = 0;
    private boolean deletable;
    private Screen screen;
    private boolean isGameRunning;

    public ServerThread(DatagramSocket socket, InetAddress address, int port, Screen screen) {
        this.socket = socket;
        this.address = address;
        this.port = port;
        deletable = false;
        socket.connect(address, port);
    	this.screen=screen;
    	isGameRunning = true;
    }

    synchronized public void run() {
        while (isGameRunning) {
            try {
            	if (screen.isGameEnded()) isGameRunning = false;
				
                byte[] bytes = new byte[1024];
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
                socket.receive(packet);
                String paddlePositionXY = new String(packet.getData(), 0, packet.getLength());
                String paddlePositionSplitted[] = paddlePositionXY.split(" ");
                paddlePoisitionX=Integer.parseInt(paddlePositionSplitted[0]);
                paddlePoisitionY=Integer.parseInt(paddlePositionSplitted[1]);
                
                byte[] b = new byte[1024];
	        	b = (screen.stringGameFullStatus()).getBytes();
	        	DatagramPacket packetBack = new DatagramPacket(b, b.length, address, port);
	        	socket.send(packetBack);           

                wait(10);
            } catch (EOFException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public int getPaddlePoisitionX() {
		return paddlePoisitionX;
	}
    
    public int getPaddlePoisitionY() {
		return paddlePoisitionY;
	}

}