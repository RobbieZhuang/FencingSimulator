/* Client.java
 *
 */

package client;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String IP = "10.242.171.92";

    // Declaring constants
    public static int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    public static int SPRITE_SIZE = SCREEN_HEIGHT / 5;
    static boolean playOnline;
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
        playOnline = playOnline();
        if (playOnline) {
            // Playing online
            clientRunning = true;
            Client client = new Client();
            client.run();
        } else {
            // Playing offline
            JFrame frame = new JFrame();
            frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

            DankTings panel = new DankTings("0", "1");
            panel.requestFocusInWindow();
            panel.go();
            frame.add(panel);



	private ClientReceiver clientReceiver;
	private ClientSender clientSender;


	public static final int WIDTH = 1080;
	public static final int HEIGHT = WIDTH /4*3;
	public static final String NAME = "Game";

	private JFrame frame;
	private BufferedImage image = new BufferedImage (WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);


	public Client (){
		setMaximumSize(new Dimension (WIDTH, HEIGHT));
		setMinimumSize (new Dimension (WIDTH, HEIGHT));
		setPreferredSize (new Dimension(WIDTH, HEIGHT));

		frame = new JFrame (NAME);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
            frame.setResizable(false);
        }
    }

    /**
     * send
     * This sends a message from client to server or physics if offline
     *
     * @param message is the player movement info, etc.
     */
    public static void send(String message) {
        clientSender.send(message);
    }

    /**
     * playOnline
     * Checks if player wants to play online/offline
     *
     * @return true if online, false if offline
     */
    private static boolean playOnline() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 0 to debug offline, 1 to play online");
        return !input.next().equals("0");
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
		try {
			socket = new Socket(IP, 6000);
			PrintWriter write = new PrintWriter(socket.getOutputStream());
			// Initiate client reader
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			clientReceiver = new ClientReceiver(input);
			clientSender = new ClientSender(write);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

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

    public void render () {

    }

    public void move(String s) {
		// Initiate client sender



	}

	public synchronized void start(){
		running = true;
		new Thread (this).start();
	}

	public synchronized void stop(){
		running = false;
	}


	void render (){

	}





	public static void main(String[] args) throws Exception {
		System.out.println("Attempting connection");
		Client client = new Client();
		client.start();
	}

	public void run (){
		System.out.println("starting thread");
		clientReceiver.start();
		clientSender.start();

		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double nsPerFrame = 1000000000D/60D;

		int frames = 0;

		double dt = 0;

		while (running){
			long now = System.nanoTime();
			dt += (now - lastTime)/nsPerFrame;
			lastTime = now;

			while (dt >= 1){
//				getOutput();
				render();
				frames++;
				dt -=1;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer+= 1000;
				System.out.println("Frames: " + frames);
				frames = 0;
			}


		}
	}



	private void getOutput() {
		System.out.println("trying to get output");
		try{
			String message = clientReceiver.getServerMessage();
			String [] args = message.trim().split("\\s+");

			int numPlayers = Integer.parseInt(args[0]);
			int [][] output = new int [numPlayers][4];
			int c = 0;
			for (int i = 1; i < args.length; i+= 4){
				output [c][0] = Integer.parseInt(args [i]);
				output [c][1] = (int)Double.parseDouble(args [i+1]);
				output [c][2] = (int)Double.parseDouble(args [i+2]);
				output [c][3] = Integer.parseInt(args [i+3]);
				c++;
			}

			for (int i = 0; i < output.length;i ++){
				System.out.println("PlayerID: " + output[i][0] + ", x: " + output[i][1] + ", y: " + output [i][2] + ", status: "+ output[i][3]);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

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
