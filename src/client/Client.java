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
	
	
	public static final int WIDTH = 1680;
	public static final int HEIGHT = WIDTH /4*3;
	public static final String NAME = "Game";

	private JFrame frame;

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
			ClientReceiver clientReceiver = new ClientReceiver(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Initiate client sender
		
		clientSender = new ClientSender(null);
		
		
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
		while (running){
			getOutput();
			render();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
private void getOutput() {
		String message = ClientReceiver.getServerMessage();
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
