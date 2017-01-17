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
		for (int i = 0; i < keys.length; i++){
			System.out.println(keys[i] + " ");
		}
	}

	public int getPlayerID() {
		return playerID;
	}

	public boolean getKey(int i) {
		return keys [i];
	}

	public int getID() {
		return this.playerID;
	} 
	
	
}
