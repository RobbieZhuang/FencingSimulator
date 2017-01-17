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
import java.util.Scanner;

public class ClientSender{
    private PrintWriter output;
    
    /**
     * Default constructor
     *
     * @param output is the PrintWriter to send messages to server
     */
    public ClientSender(PrintWriter output) {
        this.output = output;
    }
   
    public void send (String str) {
    	output.println(str);
    	output.flush();
    }
    
    public void send (long b) {
    	output.println(b);
    	output.flush();
    }
}
