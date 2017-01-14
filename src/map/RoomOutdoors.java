package map;

import java.util.LinkedList;

public class RoomOutdoors {
	private LinkedList <Land> terrain = new LinkedList<>();
	
	public RoomOutdoors () {
		terrain.add(new Land (0, 400, 800, 50, 0));
		terrain.add(new Land (900, 350, 200, 50, 0));
		terrain.add(new Land (1200, 300, 800, 50, 0));
	}
	
	public LinkedList <Land> getTerrain () {
		return terrain;
	}
}
