package server;

import java.util.ArrayList;

public class GameState {
	private ArrayList <Player> players;
	private ArrayList <Keys> keys;
	private ArrayList <Terrain> terrainBlocks;
	private ArrayList <Map> maps;
	private int currentMap;
	private int numPlayers;

	public GameState (){
		players = new ArrayList<Player>();
		keys = new ArrayList<Keys>();
		terrainBlocks = new ArrayList<Terrain>();
		maps = new ArrayList<Map>();
		numPlayers = 0;
		currentMap = 0;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void addPlayer (Player player){
		players.add(player);
		numPlayers++;
	}
	
	public void removePlayer (int playerID){
		players.remove(getPlayer(playerID));
		numPlayers--;
	}
	
	public Player getPlayer (int playerID){
		for (int i = 0; i < numPlayers; i ++){
			if (playerID == players.get(i).getID()){
				return players.get(i);
			}
		}
		return null;
	}

	public ArrayList<Keys> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<Keys> keys) {
		this.keys = keys;
	}
	
	public void addKeys (Keys keys){
		this.keys.add(keys);
	}
	
	public void removeKeys (int playerID){
		keys.remove(getKeys(playerID));
	}
	
	public Keys getKeys (int playerID){
		for (int i = 0; i < numPlayers; i ++){
			if (playerID == keys.get(i).getID()){
				return keys.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Terrain> getTerrainBlocks() {
		return terrainBlocks;
	}

	public void setTerrainBlocks(ArrayList<Terrain> terrainBlocks) {
		this.terrainBlocks = terrainBlocks;
	}

	public ArrayList<Map> getMaps() {
		return maps;
	}

	public void setMaps(ArrayList<Map> maps) {
		this.maps = maps;
	}

	public int getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public String getGameString(int playerID) {
		return getPlayer(playerID).getPlayerString();
	}
	
	public String getGameString(){
		String gameString = "";
		for (int i = 0; i < numPlayers; i ++){
			gameString += players.get(i).getID() + " " + players.get(i).getX() + " " + players.get(i).getY() + " "
					+ players.get(i).getStatus() + " ";
		}
		return gameString;
	}
}
