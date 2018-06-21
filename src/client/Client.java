
/* Client.java
 *
 * Version 1.0
 * Andi Li, Bill Li, Max Gao, Robbie Zhuang 
 * 01-23-2017
 *
 * The main class to run the client is located here.
 * This class manages connecting with server as well
 * as many of the functionality on the client side.
 */

package client;

import client.gui.Screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private static final String NAME = "Dank Tings";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static ClientReceiver clientReceiver;
    public static ClientSender clientSender;

    public static Player[] players = new Player[2];

    private String IP = "192.168.0.33";

    private boolean running;
    private Socket socket;

    private double framerate = 1000000000D / 60D;

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

        players[0] = new Player(0, 50, 50, 1);
        players[1] = new Player(1, 0, 0, 0);
    }

    /**
     * main Method
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Attempting connection");
        Client client = new Client();
        client.start();
    }


    /**
     * Listens to server and renders game screen
     */
    public void run() {

        int frames = 0;
        double framesToRender = 0;

        long lastTime = System.nanoTime();
        long lastTimer = System.currentTimeMillis();

        while (running) {

            // See how many frames are passed (probably 0.001 per iteration)
            long now = System.nanoTime();
            framesToRender += (now - lastTime) / framerate;
            lastTime = now;

            while (framesToRender >= 1) {
                getOutput();
                render();
                frames++;
                framesToRender -= 1;
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

    private void getOutput() {

        try {

            String message = clientReceiver.getServerMessage();
            String[] args = message.trim().split(" ");

            DankTings.map.setCurrentRoom(Integer.parseInt(args[1]));

            if (Integer.parseInt(args[2])%2 == 1){
            	DankTings.waitInLobby = false;
            } else {
            	DankTings.waitInLobby = true;
            }

            // Winning status
            if (Integer.parseInt(args[3]) == 0){
            	DankTings.winner = 0;
            }else if (Integer.parseInt(args[3]) == 1){
            	DankTings.winner = 1;
            }

            // Can move to next room
            if (Integer.parseInt(args[4]) == 0) {
                DankTings.canMoveToNextRoom = 0;
            } else if (Integer.parseInt(args[4]) == 1) {
                DankTings.canMoveToNextRoom = 1;
            }
            
            int c = 0;

            for (int i = 5; i < args.length; i += 4) {
                players[c].setpX(Double.parseDouble(args[i + 1]));
                players[c].setpY(Double.parseDouble(args[i + 2]));
                players[c].setStatus(Integer.parseInt(args[i + 3]));
                c++;
            }

        } catch (Exception e) {
            System.out.println("Something went wrong when trying to receive messages.\nThis is probably because you did not click play yet.");
            e.printStackTrace();
        }
    }
}