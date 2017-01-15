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
	static GameState gameState = new GameState();
	static int counter = 0;
	long end;
	long start;
	long currentTime;
	GameOutputManager (){
		
	}
	
	public static void updateGameState (int x, int y, int ID, int status){
		gameState.update(x, y, ID, status);
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
				
				
				Server.sendMessage(gameState.getString());
				try {
					Thread.sleep(10);
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