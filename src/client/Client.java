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

import javax.swing.JFrame;

public class Client {

    private static final String IP = "192.168.0.104";
    // Declaring variables
    private static volatile boolean clientRunning;
    private static Socket socket;
    private static BufferedReader input;
    private static ClientSender clientSender;

    /**
     * Main Method for BRBClient
     *
     * @param args is an array of console line arguments
     * @throws IOException to prevent exceptions caused by input and output streams
     */
    public static void main(String[] args) throws Exception {
        clientRunning = true;
        Client client = new Client();
        client.run();
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
    
    public static void send (String message) {
    	clientSender.send(message);
    }
}
