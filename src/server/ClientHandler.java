/* ServerReceiver.java
 *
 * Version 1.0
 * Bill Li, Robbie Zhuang, Kaitlyn Paglia
 * 12-23-2016
 *
 * Manages receiving messages from clients.
 */

package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    // Declaring variables

    
    private Socket connectionSocket;
    private BufferedReader input;
    private PrintWriter output;
    private Thread t;
    
    //which sprite to draw (example: jumping, moving, attacking, ducking)
    private int status;
    
    //player one or two
	private int playerID;
	private int team;
	
    //Up, Left, Down, Right, Attack, Jump
    private volatile boolean [] keys = new boolean [7];
    
    private boolean running;

    /**
     * Default constructor
     *+
     *3
     * @param connectionSocket is the connection socket hooked up to the client socket
     * @param playerID 
     * @param queue            is the server queue for messages
     * @throws IOException to prevent exceptions caused by input and output streams
     */
    ClientHandler(Socket connectionSocket, int playerID){
        status = 1;
        this.connectionSocket = connectionSocket;
        try {
			input = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
			output = new PrintWriter(this.connectionSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        running = true;
        this.playerID = playerID;
        this.team = playerID;
    }

    /**
     * Method to run the ServerReceiver
     */
    @Override
    public void run() {
    	write(""+playerID);
    	
        System.out.println("Running new ServerReceiver");
        Server.getGame().addPlayer(playerID, team);
        
        String message;

        while ((running) && (Server.running)) {
            // Listening to client for messages
            try {
            	Thread.sleep(10);
                if (input.ready()) {
                	message = input.readLine();
                    System.out.println("Message received from " + ": " + message);
                    
                    byte received = Byte.valueOf(message);
                    
                    if ((received & (1<<0)) != 0){
                    	keys [0] = true;
                    } else {
                    	keys [0] = false;
                    }                    
                    if ((received & (1<<1)) != 0){
                    	keys [1] = true;
                    } else {
                    	keys [1] = false;
                    }                    
                    if ((received & (1<<2)) != 0){
                    	keys [2] = true;
                    } else {
                    	keys [2] = false;
                    }                    
                    if ((received & (1<<3)) != 0){
                    	keys [3] = true;
                    } else {
                    	keys [3] = false;
                    }                    
                    if ((received & (1<<4)) != 0){
                    	keys [4] = true;
                    } else {
                    	keys [4] = false;
                    }                    
                    if ((received & (1<<5)) != 0){
                    	keys [5] = true;
                    } else {
                    	keys [5] = false;
                    }
                    if ((received & (1<<6)) != 0){
                    	keys [6] = true;
                    } else {
                    	keys [6] = false;
                    }
                }
            } catch (Exception e) {
                System.out.println("*** Error receiving message ***");
                e.printStackTrace();
            }
        }

        // Closing everything
        try {
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        output.close();
        System.out.println("ServerReceiver for " + this.playerID + " shuts down");
    }


    /**
     * Gets the connectionSocket
     *
     * @return the connectionSocket
     */
    public Socket getSocket() {
        return connectionSocket;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public boolean [] getKeys(){
    	return this.keys;
    }
    
	public void write(String message) {
		try {
			output.println(message);
			output.flush();
		} catch (Exception e) {
		}
	}
    
    public int getPlayerID() {
		return playerID;
	}

	void start() {
        t = new Thread(this, "cl#");
        t.run();
    }

    /**
     * Stopping the thread
     */
    void terminate() {
        running = false;
    }
}