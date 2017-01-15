/*
 * Test Git
 */
package server;


public class Game implements Runnable {
	GameState gameState;
	Thread t;
	static boolean running;
	

	Game (){
		gameState = new GameState();
		t = new Thread (new GameOutputManager(gameState));
	}


	void addPlayer (int playerID){
		gameState.addPlayer(new Player (playerID));
		gameState.addKeys(new Keys (playerID));
	}

	void removePlayer (int playerID){
		gameState.removePlayer (playerID);
		gameState.removeKeys(playerID);
	}
	void getInput (){
		for (int i = 0; i < gameState.getNumPlayers(); i ++){
			gameState.getKeys().get(i).setKeys(Server.getInput(gameState.getKeys().get(i).getPlayerID()));
		}
	}

	void updateGameState(){
		for (int i = 0; i < gameState.getNumPlayers(); i ++){
			if (gameState.getKeys().get(i).getKey(0)){
				gameState.getPlayers().get(i).moveUp();
			}
			if (gameState.getKeys().get(i).getKey(1)){
				gameState.getPlayers().get(i).moveLeft();
			}
			if (gameState.getKeys().get(i).getKey(2)){
				gameState.getPlayers().get(i).moveDown();
			}
			if (gameState.getKeys().get(i).getKey(3)){
				gameState.getPlayers().get(i).moveRight();
			}
		}
	}

	public void run (){
		running = true;
		t.start();
		while (running){

			getInput();
			updateGameState();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generat)ed catch block
				e.printStackTrace();
			}
			
		}

	}




}
