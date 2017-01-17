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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

//import physics.Physics;

public class Client {

	private static String IP = "";
	// Declaring variables
	private static volatile boolean clientRunning;
	private static Socket socket;
//	private static Physics physics;
	static boolean playOnline;

	/**
	 * Main Method for BRBClient
	 *
	 * @param args is an array of console line arguments
	 * @throws IOException to prevent exceptions caused by input and output streams
	 */
	public static void main(String[] args) throws Exception {
		IP = Inet4Address.getLocalHost().getHostAddress();
		playOnline = playOnline();
		if (playOnline) {
			clientRunning = true;
			Client client = new Client();
			client.run();
		}
	}

	/**
	 * Running the client
	 *
	 * @throws IOException for exceptions in input and output streams
	 */
	public void run() throws Exception {

		try {
			System.out.println("Attempting connection");
			socket = new Socket(IP, 6000);

			// Initiate client sender
			PrintWriter write = new PrintWriter(socket.getOutputStream());
			ClientSender sender = new ClientSender(write);

			// Initiate client reader
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ClientReceiver clientReceiver = new ClientReceiver(input);

			// Get User names from the server
			String users = clientReceiver.firstMessage();
			System.out.println("users: " + users);

			// Initiate game JFrame
			JFrame frame = new JFrame();
			frame.setSize(2000, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Initiate game JPanel
			DankTings panel = new DankTings("1", sender);
			panel.addNewPlayer("0");
			panel.addNewPlayer("1");
			panel.requestFocusInWindow();
			frame.add(panel);
			frame.setVisible(true);
			frame.setResizable(false);
			
			// Update the client receiver object with panel object
			clientReceiver.updatePanel(panel);

			Thread cr = new Thread(clientReceiver);
			cr.start();
			
			System.out.println("Connection successful");
		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}

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
