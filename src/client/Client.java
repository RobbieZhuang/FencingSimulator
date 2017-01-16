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
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import physics.Physics;

public class Client {

	private static final String IP = "192.168.0.104";
	// Declaring variables
	private static volatile boolean clientRunning;
	private static Socket socket;
	private static BufferedReader input;
	private static ClientSender clientSender;
	private static Physics physics;
	static boolean playOnline;

	/**
	 * Main Method for BRBClient
	 *
	 * @param args is an array of console line arguments
	 * @throws IOException to prevent exceptions caused by input and output streams
	 */
	public static void main(String[] args) throws Exception {
		playOnline = playOnline();
		if (playOnline) {
			clientRunning = true;
			Client client = new Client();
			client.run();
		} else {
			JFrame frame = new JFrame();
			frame.setSize(2000, 600);

			DankTings panel = new DankTings("0", "1");
			panel.requestFocusInWindow();
			panel.go();
			frame.add(panel);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.setVisible(true);
			frame.setResizable(false);
			
			physics = new Physics();
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

			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter write = new PrintWriter(socket.getOutputStream());
			clientSender = new ClientSender(write);


			System.out.println("Connection successful");
		} catch (Exception e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}

		// Receiving thread
		ClientReceiver clientReceiver = new ClientReceiver(input);
		System.out.println("Running client receiver");

		String users = clientReceiver.firstMessage();
		System.out.println("users: " + users);

		// Sending thread
		JFrame frame = new JFrame();
		frame.setSize(2000, 600);

		DankTings panel = new DankTings(users, "1");
		panel.requestFocusInWindow();
		panel.go();
		frame.add(panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		frame.setResizable(false);

		//        DankTings panel = new DankTings(users.substring(0, users.indexOf(' ')), users.substring(users.indexOf(' ')+1, users.length()));

		Thread cr = new Thread(clientReceiver);
		cr.start();

		Thread t = new Thread(panel);
		t.start();
	}
	
	public void move(String s){

	}

	/**
	 * send
	 * This sends a message from client to server or physics if offline
	 * 
	 * @param message is the player movement info, etc.
	 */
	public static void send (String message) {
		if (playOnline) {
			clientSender.send(message);
		} else {
			physics.send(message);
		}
	}

	/**
	 * playOnline
	 * Checks if player wants to play online/offline
	 * 
	 * @return true if online, false if offline
	 */
	private static boolean playOnline() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 0 to play offline, 1 to play online");
		if (input.next().equals("0")) {
			return false;
		}
		return true;
	}
}
