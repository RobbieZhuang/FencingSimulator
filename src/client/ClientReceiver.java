/* ClientReceiver.java
 *
 * Version 1.0
 * Bill Li, Robbie Zhuang, Kaitlyn Paglia
 * 12-23-2016
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
            // Attempting to receive message
            try {
                serverMessage = input.readLine();
//                if (!serverMessage.equals("-1")) {
//                    runCommands(serverMessage);
//                }
//                System.out.println("Server message: " + serverMessage);
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
    
    
    public String getServerMessage() {
		return serverMessage;
	}

//	public void updatePanel (DankTings panel) {
//    	this.panel = panel;
//    }
    
//    private void runCommands(String command){
//    	if (panel != null) {
//    		panel.updatePlayer(command);
//    	}
//    }
    
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

    public int getPlayerID () {
    	return playerID;
    }
    
}