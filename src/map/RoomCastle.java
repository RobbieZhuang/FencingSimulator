package map;

import java.util.LinkedList;

public class RoomCastle {
private LinkedList <Land> terrain = new LinkedList<>();
	
	public RoomCastle () {
		terrain.add(new Land (500, 500, 1500, 100, 0));
		terrain.add(new Land (800, 450, 400, 50, 0));
		terrain.add(new Land(0, 500, 400, 100, 0));
	}
	
	public LinkedList <Land> getTerrain () {
		return terrain;
	}
}
