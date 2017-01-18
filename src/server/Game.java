package server;

import client.DankTings;
import mechanics.Hitbox;

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
		for (int i = 0; i < gameState.getNumPlayers(); i++){
			Player p = gameState.getPlayers().get(i);
			Keys k = gameState.getKeys().get(i);
			
//			if (gameState.getKeys().get(i).getKey(0)){
//				// check collision
//				boolean collides = false;
//				gameState.getPlayers().get(i).moveUp();
//				for (int j = 0; j < gameState.getNumPlayers() && !collides; j++) {
//					if (j != i){
//						if (gameState.getPlayers().get(i).getHitbox().collidesWith(gameState.getPlayers().get(j).getHitbox())){
//							gameState.getPlayers().get(i).moveDown();
//							collides = true;
//						}
//					}
//				}
//			}
			if (k.getKey(1)){
				// check collision
				boolean collides = false;
				p.moveLeft();
				p.faceLeft();
				p.walk();
				for (int j = 0;j < gameState.getNumPlayers() && !collides; j++) {
					if (j != i){
						if (p.getHitbox().collidesWith(gameState.getPlayers().get(j).getHitbox())){
							collides = true;
							p.moveRight();
						}
					}
				}
			}
//			if (gameState.getKeys().get(i).getKey(2)){
//				boolean collides = false;
//				gameState.getPlayers().get(i).moveDown();
//				for (int j = 0;j < gameState.getNumPlayers() && !collides; j++) {
//					if (j != i){
//						if (gameState.getPlayers().get(i).getHitbox().collidesWith(gameState.getPlayers().get(j).getHitbox())){
//							collides = true;
//							gameState.getPlayers().get(i).moveUp();		
//						}
//					}
//				}
//			}
			if (k.getKey(3)){
				boolean collides = false;
				p.moveRight();
				p.faceRight();
				p.walk();
				for (int j = 0;j < gameState.getNumPlayers() && !collides; j++) {
					if (j != i){
						if (p.getHitbox().collidesWith(gameState.getPlayers().get(j).getHitbox())){
							p.moveLeft();
							collides = true;
						}
					}
				}
			}
			
			if (!k.getKey(1) && !k.getKey(3)){
				p.stand();
			}
			if (k.getKey(4)){
				gameState.getPlayers().get(i).attack();
			}
			
			if (k.getKey(5)){
				gameState.getPlayers().get(i).jump();
			}

			
			p.iterateJump();
			
			//non keys
			boolean collides = false;
			p.moveDown();
			for (int j = 0;j < gameState.getNumPlayers() && !collides; j++) {
				if (j != i){
					if (p.getY() >= DankTings.HEIGHT/2 || p.getHitbox().collidesWith(gameState.getPlayers().get(j).getHitbox())){
						collides = true;
						p.moveUp();
					}
				}
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
				e.printStackTrace();
			}
			
		}

	}




}
