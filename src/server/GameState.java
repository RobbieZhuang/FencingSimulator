package server;

import java.util.ArrayList;

import map.Map;

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
	 * updateMap
	 * This method updates the current room by getting a direction and calling the map update methods
	 * Players are respawned on either end of the maps
	 * 
	 * @param int, the direction 0 for go right, 1 to go left
	 */
	public void updateMap(int direction){
		boolean updatedRoom = false;
		// Going right (team 0)
		if (direction == 0 && map.getCurrentRoomID() < map.getNumberOfRooms()-1){
			map.moveToRightRoom();
			updatedRoom = true;
		}
		// Going left (team 1)
		else if (direction == 1 && map.getCurrentRoomID() > 0){
			map.moveToLeftRoom();
			updatedRoom = true;
		}
		if (updatedRoom){
			for (Player p: players) {
				if (p.getTeam() == 0){
					p.setX(map.getRightTeamRespawn());
					p.setY(100);
				}
				else if (p.getTeam() == 1){
					p.setY(map.getLeftTeamRespawn());
					p.setY(100);
				}
			}
		}
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
		player.setTeam(numPlayers%2);
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
