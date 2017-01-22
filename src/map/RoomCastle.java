package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class RoomCastle extends Room {
	public RoomCastle () {
		super (2000, 700);
		LinkedList <Land> terrain = super.getTerrain();
		terrain.add(new Land (0, 0, 1500, 100, 0));
		terrain.add(new Land (800, 450, 400, 50, 0));
        terrain.add(new Land(0, 500, 4000, 100, 0));
        terrain.add(new Land (800, 0, 600, 200, 0));
		terrain.add(new Land (0, 0, 2000, 50, 0));
        terrain.add(new Land(-10, 0, 5, 1000, 0));
        terrain.add(new Land(2000, 0, 5, 1000, 0));

		generateRespawnPoints();
	}

	@Override
	public void drawRoom(int lX, int tY, Graphics g) {
		LinkedList <Land> terrain = super.getTerrain();
		g.setColor(Color.gray);
		for (int a = 0; a < terrain.size(); a++) {
			Land l = terrain.get(a);
			g.fillRect(l.getlX()-lX, l.getlY()-tY, l.getLength(), l.getHeight());
		}
	}

	@Override
	protected void generateRespawnPoints() {
		super.getRespawn().add(new Point(100, 500));
		super.getRespawn().add(new Point(1900, 500));
		super.getRespawn().add(new Point (1000, 400));
	}

	@Override
	public int leftTarget() {
		return 50;
	}

	@Override
	public int rightTarget() {
		return 1870;
	}
}