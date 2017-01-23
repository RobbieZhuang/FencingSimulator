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

import client.gui.Screen;

import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//import physics.Physics;

public class Client implements Runnable {
    public static final String NAME = "Game";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static ClientReceiver clientReceiver;
    public static ClientSender clientSender;
    //	private ArrayList <PlayerImage> players = new ArrayList <PlayerImage>();
    public static PlayerImage[] players = new PlayerImage[2];
    //	private static Physics physics;
    static boolean playOnline;
    private String IP = "";
    // Declaring variables
    private boolean running;
    private Socket socket;
    private JFrame frame;
    private DankTings jpanel;


    public Client() {

        // Running GUI
        Screen screen = new Screen(NAME);

        try {
            socket = new Socket(IP, 6000);
            socket.setTcpNoDelay(true);
            PrintWriter write = new PrintWriter(socket.getOutputStream());
            // Initiate client reader
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientReceiver = new ClientReceiver(input);
            clientSender = new ClientSender(write);

            System.out.println("starting thread");
            clientReceiver.start();
            clientSender.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

//		jpanel = new DankTings(clientReceiver.getPlayerID(), clientSender, players);
//		frame.add(jpanel, BorderLayout.CENTER);
//		jpanel.requestFocusInWindow();

        players[0] = new PlayerImage(0, 50, 50, 1);
        players[1] = new PlayerImage(1, 0, 0, 0);

        // Initiate client sender


    }

	public static void main(String[] args) throws Exception {
		System.out.println("Attempting connection");
		Client client = new Client();
		client.start();
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

    public void run() {

        long lastTime = System.nanoTime();
        long lastTimer = System.currentTimeMillis();
        double nsPerFrame = 1000000000D / 60D;

        int frames = 0;

        double dt = 0;

        while (running) {
            long now = System.nanoTime();
            dt += (now - lastTime) / nsPerFrame;
            lastTime = now;

            while (dt >= 1) {
                getOutput();
                render();
                frames++;
                dt -= 1;
            }
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println("Frames: " + frames);
                frames = 0;
            }


        }
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }

    void render() {
        Screen.contentPane.repaint();
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

    private void getOutput() {
        try {
            String message = clientReceiver.getServerMessage();
            System.out.println("Message From Server: " + message);
            String[] args = message.trim().split(" ");
            DankTings.map.setCurrentRoom(Integer.parseInt(args[1]));
            System.out.println("THE CURRENT ROOM IS: " + args[1]);
            int c = 0;
            for (int i = 2; i < args.length; i += 4) {
                players[c].setpX(Double.parseDouble(args[i + 1]));
                players[c].setpY(Double.parseDouble(args[i + 2]));
                players[c].setStatus(Integer.parseInt(args[i + 3]));
//				System.out.println("PlayerID: " + players[c].getPlayerID()
//						+ "x: " + players[c].getpX()
//						+ "y: " + players[c].getpY()
//						+ "status: " + players[c].getStatus());
                c++;
            }
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to receive messages.\nThis is probably because you did not click play yet.");
            e.printStackTrace();
        }
    }
}