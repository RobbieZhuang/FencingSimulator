package server;

public class Keys {
	private boolean [] keys;
	private int playerID;
	
	public Keys (int playerID){
		keys = new boolean [6];
		this.playerID = playerID;
	}

	public boolean[] getKeys() {
		return keys;
	}

	public void setKeys(boolean[] keys) {
		this.keys = keys;
	}

	public int getPlayerID() {
		return playerID;
	}

	public boolean getKey(int i) {
		return keys [i];
	} 
	
	
}
