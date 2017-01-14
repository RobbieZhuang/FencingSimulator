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
    private String player1;
    private String player2;
    
    private long start;
    private long end;
    
    

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
        while (running) {
            // Attempting to receive message
            try {
                String serverMessage = input.readLine();
                if (!serverMessage.equals("-1")) {
                    runCommands(serverMessage);
                }
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
    void start() {
        t = new Thread(this, "cr#");
        t.run();
    }

    /**
     * Ending the thread
     */
    void terminate() {
        running = false;
    }
    private void runCommands(String command){
    	//if (command.substring(0, command.indexOf(' ')).equals("updating")) {
    		DankTings.updatePlayer(command/*.substring(command.indexOf(' ') + 1)*/);
    	//}
    }
    
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
    
}
