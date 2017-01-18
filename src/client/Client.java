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

import javax.swing.JFrame;

//import physics.Physics;

public class Client extends Canvas implements Runnable{
	private static String IP = "";
	// Declaring variables
	private static volatile boolean running;
	private static Socket socket;
	//	private static Physics physics;
	static boolean playOnline;





	private ClientReceiver clientReceiver;
	private ClientSender clientSender;


	public static final int WIDTH = 1080;
	public static final int HEIGHT = WIDTH /4*3;
	public static final String NAME = "Game";

	private JFrame frame;
	private BufferedImage image = new BufferedImage (WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
		
	public Client (){
		setMaximumSize(new Dimension (WIDTH, HEIGHT));
		setMinimumSize (new Dimension (WIDTH, HEIGHT));
		setPreferredSize (new Dimension(WIDTH, HEIGHT));

		frame = new JFrame (NAME);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);


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

		// Initiate client sender



	}

	public synchronized void start(){
		running = true;
		new Thread (this).start();
	}

	public synchronized void stop(){
		running = false;
	}


	void render (){

	}





	public static void main(String[] args) throws Exception {
		System.out.println("Attempting connection");
		Client client = new Client();
		client.start();
	}

	public void run (){
		System.out.println("starting thread");
		clientReceiver.start();
		clientSender.start();
		
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
//				getOutput();
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



	private void getOutput() {
		System.out.println("trying to get output");
		try{
			String message = clientReceiver.getServerMessage();
			String [] args = message.trim().split("\\s+");

			int numPlayers = Integer.parseInt(args[0]);
			int [][] output = new int [numPlayers][4];
			int c = 0;
			for (int i = 1; i < args.length; i+= 4){
				output [c][0] = Integer.parseInt(args [i]);
				output [c][1] = (int)Double.parseDouble(args [i+1]);
				output [c][2] = (int)Double.parseDouble(args [i+2]);
				output [c][3] = Integer.parseInt(args [i+3]);
				c++;
			}

			for (int i = 0; i < output.length;i ++){
				System.out.println("PlayerID: " + output[i][0] + ", x: " + output[i][1] + ", y: " + output [i][2] + ", status: "+ output[i][3]);
			}
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