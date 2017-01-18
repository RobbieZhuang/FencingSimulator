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

			if (k.getKey(1) && !p.getAttacking()){
				// check collision
				boolean collides = false;
				p.moveLeft();
				p.faceLeft();
				p.walk();
				for (int j = 0;j < gameState.getRoom().getTerrain().size() && !collides; j++) {
					if (p.getHitbox().collidesWith(gameState.getRoom().getTerrain().get(j).getHitbox())){
						collides = true;
						p.moveRight();
					}
				}
			}

			if (k.getKey(3) && !p.getAttacking()){
				boolean collides = false;
				p.moveRight();
				p.faceRight();
				p.walk();
				for (int j = 0;j < gameState.getRoom().getTerrain().size() && !collides; j++) {
					if (p.getHitbox().collidesWith(gameState.getRoom().getTerrain().get(j).getHitbox())){
						collides = true;
						p.moveLeft();
					}
				}
			}

			if (!k.getKey(1) && !k.getKey(3)){
				p.stand();
			}

			p.iterateAttack();

			if (k.getKey(4)){
				gameState.getPlayers().get(i).attack();
			}

			if (k.getKey(5)){
				gameState.getPlayers().get(i).jump();
			}

			//non keys
			boolean collides = false;
			p.iterateJump();
			for (int j = 0;j < gameState.getRoom().getTerrain().size() && !collides; j++) {
				if (p.getHitbox().collidesWith(gameState.getRoom().getTerrain().get(j).getHitbox())){
					collides = true;
					p.moveDown();
					p.moveDown();
					p.moveDown();
					p.moveDown();
				}
			}


			collides = false;
			p.moveDown();
			for (int j = 0;j < gameState.getRoom().getTerrain().size() && !collides; j++) {
				if (p.getHitbox().collidesWith(gameState.getRoom().getTerrain().get(j).getHitbox())){
					collides = true;
					p.moveUp();
					System.out.println("detecing collision43545345235454h4g");
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
