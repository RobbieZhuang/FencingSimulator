package map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class RoomOutdoors extends Room {
	
	public RoomOutdoors () {
		super (3000, 700);
		LinkedList <Land> terrain = super.getTerrain();
		terrain.add(new Land (0, 400, 800, 50, 0));
		terrain.add(new Land (900, 350, 200, 50, 0));
		terrain.add(new Land (1200, 300, 800, 50, 0));
		
	}
	
	@Override
	public void drawRoom(int lX, int tY, Graphics g) {
		LinkedList <Land> terrain = super.getTerrain();
		g.setColor(new Color (0,51,0));
		for (int a = 0; a < terrain.size(); a++) {
			Land l = terrain.get(a);
			g.fillRect(l.getlX()-lX, l.getlY()-tY, l.getLength(), l.getHeight());
		}
	}
}