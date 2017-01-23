package server;

import map.Map;

import java.util.ArrayList;

public class GameState {
	
	private ArrayList <Player> players;
	private ArrayList <Keys> keys;
	
	// Declare map variable
	private Map map;
	private int numPlayers;
	// Zero for true (wait), One for false
	private int waitInLobby = 0;
	// -1 for none, 0 for 0, 1 for 1
    private int winner = -1;
    // -1 for none, 0 for 0, 1 for 1
    private int canMoveToNextRoom = -1;

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
	 * updateRoom
	 * This method updates the current room by getting a direction and calling the map update methods
	 * Players are spawned on either end of the maps
	 * 
	 * @param int, the direction 0 for go right, 1 to go left
	 */
	public void updateRoom(int direction){
		boolean updatedRoom = false;
		// Going right (team 0)
		if (direction == 0 && canMoveToNextRoom == 0){
			if (map.getCurrentRoomID() < map.getNumberOfRooms()-1){
				map.moveToRightRoom();
				updatedRoom = true;
			}
			else{
				winner = 0;
			}
		}
		// Going left (team 1)
		else if (direction == 1 && canMoveToNextRoom == 1){
			if (map.getCurrentRoomID() > 0){
				map.moveToLeftRoom();
				updatedRoom = true;
			}
			else{
				winner = 1;
			}
		}
		if (updatedRoom){
			for (Player p: players) {
				if (p.getTeam() == 0){
					p.setX(map.getRightTeamRespawn().x);
					p.setY(map.getRightTeamRespawn().y);
				}
				else if (p.getTeam() == 1){
					p.setX(map.getLeftTeamRespawn().x);
					p.setY(map.getLeftTeamRespawn().y);
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
		if (numPlayers == 2){
			waitInLobby = 1;
		}
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
		String gameString = players.size() + " " + map.getCurrentRoomID() + " " + waitInLobby + " " + winner + " " + canMoveToNextRoom + " ";
		for (int i = 0; i < numPlayers; i ++){
			gameString += players.get(i).getID() + " " + players.get(i).getX() + " " + players.get(i).getY() + " "
					+ players.get(i).getStatus() + " ";
		}
		return gameString;
	}
	
	public void setCanMoveToNextRoom(int i){
		this.canMoveToNextRoom = i;
	}
}
