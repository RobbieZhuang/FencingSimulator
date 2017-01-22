package server;

public class Game implements Runnable {
    static boolean running;
    GameState gameState;
	Thread t;


	Game (){
		gameState = new GameState();
		t = new Thread (new GameOutputManager(gameState));
	}


	void addPlayer (int playerID, int team){
		gameState.addPlayer(new Player (playerID, team));
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
	
	/**
	 * updateRoom
	 * Updates the room that the players are in.
	 * Once on player reaches the other side 
	 */
	void updateRoom(){
		
	}
	void updateGameState(){
		for (int i = 0; i < gameState.getNumPlayers(); i++){
			Player p = gameState.getPlayers().get(i);
			Keys k = gameState.getKeys().get(i);

			
			// Updating which room the players should be in by figuring out if they walked far enough
			// Zero means team 0 which starts left and goes right
			// One means team 1 which starts right and goes left
			if (p.getTeam() == 0){
				if (p.getX() >= gameState.getMap().getCurrentRoom().rightTarget()){
					gameState.updateMap(0);
				}
			}else if (p.getTeam() == 1){
				if (p.getX() <= gameState.getMap().getCurrentRoom().leftTarget()){
					gameState.updateMap(1);
				}
			}
			p.iterateCounters();
			if (p.isAlive()){
				if (k.getKey(1) && !p.getAttacking()){
					// check collision
					boolean collides = false;
					p.moveLeft();
					p.faceLeft();
					p.walk();
					for (int j = 0;j < gameState.getMap().getCurrentRoom().getTerrain().size() && !collides; j++) {
						if (p.getHitbox().collidesWith(gameState.getMap().getCurrentRoom().getTerrain().get(j).getHitbox())){
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
					for (int j = 0;j < gameState.getMap().getCurrentRoom().getTerrain().size() && !collides; j++) {
						if (p.getHitbox().collidesWith(gameState.getMap().getCurrentRoom().getTerrain().get(j).getHitbox())){
							collides = true;
							p.moveLeft();
						}
					}
				}

				if (!k.getKey(1) && !k.getKey(3)){
					p.stand();
				}

				

				if (k.getKey(4)){
					gameState.getPlayers().get(i).attack();
					if (p.isFacingLeft()){
						p.moveLeft();
						p.moveLeft();
						p.moveLeft();
						for (int j = 0; j < gameState.getNumPlayers(); j++) {
							if (i != j){
								if (p.getHitbox().collidesWith(gameState.getPlayers().get(j).getHitbox())){
                                    if (gameState.getPlayers().get(j).isAlive()) {
                                        gameState.getPlayers().get(j).dead();
                                        gameState.getPlayer(i).increaseTotalNumberOfKills();
                                    }
                                    System.out.println(j + " is dead " + gameState.getPlayers().get(j).isAlive());
								}
							}
						}
						p.moveRight();
						p.moveRight();
						p.moveRight();
					}
					else{
						p.moveRight();
						p.moveRight();
						p.moveRight();
						for (int j = 0; j < gameState.getNumPlayers(); j++) {
							if (i != j){
								if (p.getHitbox().collidesWith(gameState.getPlayers().get(j).getHitbox())){
                                    if (gameState.getPlayers().get(j).isAlive()) {
                                        gameState.getPlayers().get(j).dead();
                                        gameState.getPlayer(i).increaseTotalNumberOfKills();
                                    }
                                    System.out.println(j + " is dead " + gameState.getPlayers().get(j).isAlive());
								}
							}
						}
						p.moveLeft();
						p.moveLeft();
						p.moveLeft();
					}
				}

				if (k.getKey(5)){
					gameState.getPlayers().get(i).jump();
				}

				//non keys
				boolean collides = false;
				for (int j = 0;j < gameState.getMap().getCurrentRoom().getTerrain().size() && !collides; j++) {
					if (p.getHitbox().collidesWith(gameState.getMap().getCurrentRoom().getTerrain().get(j).getHitbox())){
						collides = true;
						p.moveDown();
						p.moveDown();
						p.moveDown();
						p.moveDown();
					}
				}
				
				//gravity
				collides = false;
				p.moveDown();
				for (int j = 0;j < gameState.getMap().getCurrentRoom().getTerrain().size() && !collides; j++) {
					if (p.getHitbox().collidesWith(gameState.getMap().getCurrentRoom().getTerrain().get(j).getHitbox())){
						collides = true;
						p.moveUp();
						p.setOnGround(true);
						System.out.println("detecing collision43545345235454h4g");
					}
					else{
						p.setOnGround(false);
					}
				}
			}
			
			// THIS IS WHERE I CAN'T RESPAWN THE DUDE
			if (!p.isAlive()){
				p.basicRevive();
				System.out.println(gameState.getMap().getCurrentRoom().getNearestRespawn((int)p.getX()).x);
				System.out.println(gameState.getMap().getCurrentRoom().getNearestRespawn((int)p.getX()).y);
//				p.revive(gameState.getRoom().getNearestRespawn((int)p.getX()).x,gameState.getRoom().getNearestRespawn((int)p.getX()).y);
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
