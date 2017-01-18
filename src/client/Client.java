/* BRBClient.java
 *
 * Version 1.0
 * Bill Li, Robbie Zhuang, Kaitlyn Paglia
 * 12-23-2016
 *
 * The main class to run the client is located here.
 * This class manages connecting with server as well
 * as many of the functionality on the client side.
 */

package client;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

//import physics.Physics;

public class Client implements Runnable{
	private String IP = "";
	// Declaring variables
	private volatile boolean running;
	private Socket socket;
	//	private static Physics physics;
	static boolean playOnline;

	
	
	private ClientReceiver clientReceiver;
	private ClientSender clientSender;

	private ArrayList <PlayerImage> players = new ArrayList <PlayerImage>();

	public static final String NAME = "Game";

	private JFrame frame;
	private DankTings jpanel;
	
		
	public Client (){

		try {
			socket = new Socket(IP, 6000);
			PrintWriter write = new PrintWriter(socket.getOutputStream());
			// Initiate client reader
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			clientReceiver = new ClientReceiver(input);
			clientSender = new ClientSender(write);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new JFrame (NAME);
//		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jpanel = new DankTings(clientSender, players);
		frame.add(jpanel, BorderLayout.CENTER);
		jpanel.requestFocusInWindow();
		
		frame.setSize(new Dimension(DankTings.WIDTH, DankTings.HEIGHT));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);



		// Initiate client sender



	}

	public static void main(String[] args) throws Exception {
		System.out.println("Attempting connection");
		Client client = new Client();
		client.start();
	}

	public void run (){
		System.out.println("starting thread");
		clientReceiver.start();
		
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double nsPerFrame = 1000000000D/60D;
		
		int frames = 0;
		
		double dt = 0;
		
		while (running){
			long now = System.nanoTime();
			dt += (now - lastTime)/nsPerFrame;
			lastTime = now;
			
			while (dt >= 1){
				getOutput();
				render();
				frames++;
				dt -=1;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer+= 1000;
				System.out.println("Frames: " + frames);
				frames = 0;
			}


		}
	}

	public synchronized void start(){
		running = true;
		new Thread (this).start();
	}

	public synchronized void stop(){
		running = false;
	}


	void render (){
		jpanel.repaint();
	}


	private void getOutput() {
		try{
			String message = clientReceiver.getServerMessage();
			String [] args = message.trim().split("\\s+");

			int numPlayers = Integer.parseInt(args[0]);
			for (int i = 1; i < args.length; i+= 4){
				int playerID = Integer.parseInt(args [i]);
				double x = Double.parseDouble(args [i+1]);
				double y= (int)Double.parseDouble(args [i+2]);
				int status = Integer.parseInt(args [i+3]);
				
				boolean set = false;
				for (PlayerImage p: players){
					if (p.getPlayerID() == playerID){
						p.setpX(x);
						p.setpY(y);
						p.setStatus(status);
						set = true;
					}
				}
				
				if (!set){
					System.out.println("Adding new player image");
					players.add(new PlayerImage (playerID, x, y, status));
				}
			}


//			for (int i = 0; i < players.size();i ++){
//				System.out.println("NUM PLAYERS: " + players.size() +" PlayerID: " + players.get(i).getPlayerID()
//						+ ", x: " + players.get(i).getpX()
//						+ ", y: " + players.get(i).getpX()
//						+ ", status: "+ players.get(i).getStatus());
//			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	//	/**
	//	 * Running the client
	//	 *
	//	 * @throws IOException for exceptions in input and output streams
	//	 */
	//	public void run() {
	//
	//		try {
	//			
	//
	//
	//			// Update the client receiver object with panel object
	//			clientReceiver.updatePanel(panel);
	//
	//			Thread cr = new Thread(clientReceiver);
	//			cr.start();
	//
	//			System.out.println("Connection successful");
	//		} catch (Exception e) {
	//			System.out.println("Connection failed");
	//			e.printStackTrace();
	//		}
	//
	//		//main render loop
	//		while (running){
	//
	//		}
	//
	//	}

	/**
	 * playOnline
	 * Checks if player wants to play online/offline
	 * 
	 * @return true if online, false if offline
	 */
	private static boolean playOnline() {
		return true;
		//		Scanner input = new Scanner(System.in);
		//		System.out.println("Enter 0 to play offline, 1 to play online");
		//		if (input.next().equals("1")) {
		//			return true;
		//		}
		//		return false;
	}
}