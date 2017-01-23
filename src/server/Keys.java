/* Keys.java
 *
 * Version 1.0
 * Max Gao, Andi Li, Bill Li, Robbie Zhuang
 * 01-23-17
 *
 * Keys pressed object
 */
package server;

public class Keys {
	private boolean [] keys;
	private int playerID;
	
	public Keys (int playerID){
		keys = new boolean [7];
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

	public int getID() {
		return this.playerID;
	} 
	
	
}
