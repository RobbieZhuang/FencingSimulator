/* ClientSender.java
 *
 * Version 1.0
 * Bill Li, Robbie Zhuang, Kaitlyn Paglia
 * 12-23-2016
 *
 * This is code for a thread to send messages on the client side
 */

package client;

import java.io.PrintWriter;

public class ClientSender implements Runnable {
    private PrintWriter output;
    private byte message;
    private boolean running;
    
    /**
     * Default constructor
     *
     * @param output is the PrintWriter to send messages to server
     */
    public ClientSender(PrintWriter output) {
        this.output = output;
    }
   
    public byte getMessage() {
		return message;
	}

	public void setMessage(byte message) {
		this.message = message;
	}

	public void send (String str) {
    	output.println(str);
    	output.flush();
    }
    
    public void send (long b) {
    	output.println(b);
    	output.flush();
    }

    public void start(){
    	Thread t = new Thread (this);
    	running = true;
    	t.start();
    }
    
    public void stop(){
    	running = false;
    }
    
	@Override
	public void run() {
		while (running){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			send(message);

            // Making sure that it only makes on player at a time
            if ((message & (1 << 7)) != 0) {
                message = 0;
            }
        }
	}
 }