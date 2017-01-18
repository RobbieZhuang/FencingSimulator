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
		clients = new ArrayList <ClientHandler>();
		game = new Game ();
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
		for (int i = 0; i < clients.size(); i++){
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

	private void run() {
		try {
			System.out.println("Started server");
			//starts the server output manager

			Thread t = new Thread (game);

			// Constantly trying to allow clients to connect to server
			while (running) {
				try {
					// Adding new clients to server
					Socket connectionSocket1 = socketServer.accept();
					ClientHandler clientHandler1 = new ClientHandler(connectionSocket1, 0);
					Thread c1 = new Thread(clientHandler1);
					c1.start();

					clients.add(clientHandler1);



					System.out.println("Connection successful");

					// Adding new clients to server
					Socket connectionSocket2 = socketServer.accept();
					ClientHandler clientHandler2 = new ClientHandler(connectionSocket2, 1);
					Thread c2 = new Thread(clientHandler2);
					c2.start();

					clients.add(clientHandler2);

					t.start();
					
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

	public static Game getGame() {
		return game;
	}
}
