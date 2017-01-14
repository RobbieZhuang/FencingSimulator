/* QuitListener.java
 *
 * Version 1.0
 * Bill Li, Robbie Zhuang, Kaitlyn Paglia
 * 12-23-2016
 *
 * This class properly closes all the functions of the program when run.
 */

package server;

import java.util.Scanner;

public class QuitListener implements Runnable {
    boolean running;
    Thread t;

    /**
     * What the thread does, which is just listen for quit
     */
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        while (Server.serverRunning) {
            String response = in.next();
            if (response.equalsIgnoreCase("quit")) {
                // Last words before server shuts down
                System.out.println("Server shutting down..");
                Server.queue.enqueue("Server#$Server will shut down in 5s");
                while (!Server.queue.isEmpty()) {
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Closing things
                Server.closeSocketServer();
                Server.serverRunning = false;
            }
        }
        System.out.println("QuitListener should end");
    }

    /**
     * Starts the thread
     */
    public void start() {
        t = new Thread(this, "QuitListener");
        t.run();
    }
}
