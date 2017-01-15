/*
 * Test Git
 */
package server;

import java.util.ArrayList;

public class Game implements Runnable {
	
	static ArrayList <Player> players;
	static ArrayList <Keys> keys;
	static ArrayList <Terrain> terrainBlocks;
	static ArrayList <Map> maps;
	static int currentMap;
	static boolean running;
	int numPlayers;

	Game (){
		initialize();


	}

	void initialize(){
		numPlayers = 0;
		terrainBlocks = new ArrayList <Terrain>();
		players = new ArrayList <Player>();
		keys = new ArrayList <Keys>();
	}

	static void addPlayer (int playerID){
		players.add(new Player(playerID));
		keys.add(new Keys (playerID));

	}

	public static void removePlayer (int id){

	}
	void getInput (){

		for (int i = 0; i < players.size(); i ++){
			keys.get(i).setKeys(Server.getInput(keys.get(i).getPlayerID()));
		}

	}

	void updateGameState(){
		for (int i = 0; i < players.size(); i ++){
			if (keys.get(i).getKey(0)){
				players.get(i).moveUp();
			}
			if (keys.get(i).getKey(1)){
				players.get(i).moveLeft();
			}
			if (keys.get(i).getKey(2)){
				players.get(i).moveDown();
			}
			if (keys.get(i).getKey(3)){
				players.get(i).moveRight();
			}
		}
	}

	void outputGameState(){		
		for (int i = 0; i < players.size(); i ++){
			GameOutputManager.updateGameState(players.get(i).getX(), players.get(i).getY(), players.get(i).getID(), players.get(i).getID());
		}
	}

	public void run (){
		running = true;
		while (running){

			getInput();
			updateGameState();
			outputGameState();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}




}
