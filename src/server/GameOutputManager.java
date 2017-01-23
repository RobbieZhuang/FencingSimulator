/* ServerSender.java
 *
 * Version 1.0
 * Bill Li, Robbie Zhuang, Kaitlyn Paglia
 * 12-23-2016
 *
 * Manages sending messages to clients.
 */

package server;


public class GameOutputManager implements Runnable {
	static int counter = 0;
    GameState gameState;
    long end;
	long start;
	long currentTime;
	
	GameOutputManager (GameState gameState){
		this.gameState = gameState;
	}
	
	/**
	 * This is the new thread
	 */
	@Override
	public void run() {
		System.out.println("Running ServerSender");
		while (Server.running) {
//				System.out.println(" Sending: " + gameState.getString());
				
			if (counter%10 == 0) {
				start = System.currentTimeMillis();
			}
			counter++;
				
//				for (int i = 0; i < gameState.getNumPlayers(); i ++){
//					Server.sendMessage(gameState.getPlayers().get(i).getPlayerString());
//				}

            String message = gameState.getGameString();
            System.out.println("Message that will be sent: " + message);
            Server.sendMessage(message);
            try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (counter%10 == 0) {
					end = System.currentTimeMillis();
					System.out.println("Outputs per second: " + (int)(10*1000/(end - start)));
					counter = 0;
				}
		}
	}

}