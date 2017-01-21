package server;

import java.util.ArrayList;

import map.Map;
import map.Room;
import map.RoomCastle;
import map.RoomOutdoors;

public class GameState {
	
	private ArrayList <Player> players;
	private ArrayList <Keys> keys;
	
	// Declare map variable
	private static Map map;
	private int numPlayers;

	public GameState (){
		players = new ArrayList<Player>();
		keys = new ArrayList<Keys>();
		map = new Map();
		numPlayers = 0;
	}
	
	/**
	 * getMap
	 * This method returns the map object
	 * @return Map
	 */
	public Map getMap(){
		return this.map;
	}
	
	/**
	 * Player methods
	 * @return
	 */
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

	/**
	 * Key methods
	 * @return
	 */
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

	public int getNumPlayers() {
		return numPlayers;
	}
	
	/**
	 * getGameString
	 * concatenates the string to be sent to the client
	 * @return String
	 */
	public String getGameString(int playerID) {
		return getPlayer(playerID).getPlayerString();
	}
	
	/**
	 * getGameString
	 * concatenates the string to be sent to the client
	 * @return String
	 */
	public String getGameString(){
		String gameString = players.size() + " " + map.getCurrentRoomID() + " ";
		for (int i = 0; i < numPlayers; i ++){
			gameString += players.get(i).getID() + " " + players.get(i).getX() + " " + players.get(i).getY() + " "
					+ players.get(i).getStatus() + " ";
		}
		return gameString;
	}
}
