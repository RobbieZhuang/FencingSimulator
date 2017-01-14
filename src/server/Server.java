package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import org.omg.CORBA.INITIALIZE;

public class Server {
	// Creating variables
	static ServerSocket socketServer;
	static volatile ArrayList <ClientHandler> clients;
	static GameOutputManager outputManager;
	static boolean running = true;
	static Game game;
	

	
	
	public static void main (String args[]){
		Server server = new Server();
		server.initialize();
		server.run();
	}

	private void initialize(){
		try {
			socketServer = new ServerSocket (6000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game = new Game ();
		clients = new ArrayList <ClientHandler>();
	}

	/**
	 * Closing the socketServer
	 */
	static void closeSocketServer() {
		try {
			socketServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendMessage (String message){
		for (int i = 0; i < clients.size(); i ++){
			clients.get(i).write(message);
		}
	}

	private static ClientHandler getClient (int playerID){
		for (int i = 0; i < clients.size(); i ++){
			if (clients.get(i).getPlayerID() == playerID){
				return clients.get(i);
			}
		}
		System.out.println("clienthandler not found");
		return null;
	}
	
	public static boolean [] getInput (int playerID){
		return getClient(playerID).getKeys();
	}

	public static void startOutputManager (){
		Thread t = new Thread (new GameOutputManager ());
		t.start();
	}

	private void run() {
		try {
			System.out.println("Started server");
			//starts the server output manager

			Thread f = new Thread (new GameOutputManager());
			Thread t = new Thread (game);
			
			// Constantly trying to allow clients to connect to server
			int counter = 0;
			while (running) {
				try {
					
					
					// Adding new clients to server
					Socket connectionSocket = socketServer.accept();
					ClientHandler clientHandler = new ClientHandler(connectionSocket, counter);
					Thread cl = new Thread(clientHandler);
					cl.start();
					
					clients.add(clientHandler);
					if (counter == 0){
						f.start();
						t.start();
					}
					
					counter++;
					System.out.println("Connection successful");
				} catch (SocketException se) {
					System.out.println("SocketServer has closed");
				} catch (Exception e) {
					System.out.println("*** Error connecting client to server ***");
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// The end of server
		try {
			socketServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("The server should end");
	}
}
