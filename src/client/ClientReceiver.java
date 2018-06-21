
/* ClientReceiver.java
 *
 * Version 1.0
 * Andi Li, Bill Li, Max Gao, Robbie Zhuang 
 * 01-23-2017
 *
 * This is code for a thread to receive messages on the client side
 */

package client;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ClientReceiver implements Runnable {

    private Thread t;
    private BufferedReader input;
    private volatile boolean running = true;
    private String serverMessage = "";
    private int playerID = -999;

    /**
     * Default constructor
     *
     * @param input is the BufferedReader to read messages from server
     */
    ClientReceiver(BufferedReader input) {
        this.input = input;
    }

    /**
     * This is used to run a new thread
     */
    @Override
    /**
     * run
     */
    public void run() {
    	boolean messageReceived = false;
    	while (!messageReceived) {
    		try {
                String message = input.readLine();
                System.out.println(message);
                if (!(message.equals("-1"))) {
                    playerID = Integer.parseInt(message);
                    messageReceived = true;
                }
            } catch (Exception e) {
                System.out.println("*** Error receiving message ***");
                e.printStackTrace();
            }
    	}
        while (running) {
            try {
                serverMessage = input.readLine();
            } catch (Exception e) {
                System.out.println("*** Error receiving message ***");
                e.printStackTrace();
            }
        }
        System.out.println("ClientReceiver shut down");
    }

    /**
     * Starting the thread
     */
    public void start() {
        t = new Thread(this, "cr#");
        t.start();
    }

    /**
     * Ending the thread
     */
    public void terminate() {
        running = false;
    }
    
    /**
     * getServerMessage
     * @return string
     */
    public String getServerMessage() {
		return serverMessage;
	}

    /**
     * firstMessage
     * @return
     */
    public String firstMessage () {
    	while (true) {
    		 try {
                 if (input.ready()) {
                     String serverMessage = input.readLine();
                     return serverMessage;
                 }
             } catch (Exception e) {
                 System.out.println("*** Error receiving message ***");
                 e.printStackTrace();
             }
    	}
    }

    /**
     * getPlayerID
     * @return int
     */
    public int getPlayerID () {
    	return playerID;
    }
    
}