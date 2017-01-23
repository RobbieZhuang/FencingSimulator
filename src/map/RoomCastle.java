package map;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

public class RoomCastle extends Room {
	private int RUBBLE_LENGTH = 15;
	private int RUBBLE_HEIGHT = 3;
	
	private LinkedList <Point> rubble;
	public RoomCastle () {
		super (2000, 700);
		rubble = new LinkedList<>();
		LinkedList <Land> terrain = super.getTerrain();
		terrain.add(new Land (0, 0, 1500, 100, 0));
		terrain.add(new Land (800, 450, 400, 50, 0));
        terrain.add(new Land(0, 500, 4000, 100, 0));
        terrain.add(new Land (800, 0, 600, 200, 0));
		terrain.add(new Land (0, 0, 2000, 50, 0));
        terrain.add(new Land(-10, 0, 5, 1000, 0));
        terrain.add(new Land(2000, 0, 5, 1000, 0));

        for (int a = 0; a < super.getTerrain().size(); a++) {
        	Land l = super.getTerrain().get(a);
        	int nLoops = (int)(Math.random()*l.getLength()/50.0)+1;
        	for (int b = 0; b < nLoops; b++) {
        		int x = (int)(Math.random()*l.getLength()) + l.getlX();
        		int y = l.getlY()-RUBBLE_HEIGHT;
        		rubble.add(new Point(x, y));
        	}
        }
        
		generateRespawnPoints();
	}

	@Override
	public void drawRoom(int lX, int tY, Graphics g) {
		LinkedList <Land> terrain = super.getTerrain();
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, this.getHeight(), new Color (20,20,20), 0, 0, new Color (160,160,160)));
		for (int a = 0; a < terrain.size(); a++) {
			Land l = terrain.get(a);
			g2.fillRect(l.getlX()-lX, l.getlY()-tY, l.getLength(), l.getHeight());
		}
		for (int a = 0; a < rubble.size(); a++) {
			int x = (int)(rubble.get(a).getX());
			int y = (int)(rubble.get(a).getY());
			g2.fillRect(x-lX, y-tY, RUBBLE_LENGTH, RUBBLE_HEIGHT);
		}
	}

	@Override
	protected void generateRespawnPoints() {
		super.getRespawn().add(new Point(100, 500));
		super.getRespawn().add(new Point(1900, 500));
		super.getRespawn().add(new Point (1000, 400));
	}

	@Override
	public Point leftTarget() {
		return new Point(50, 50);
	}
	@Override
	public Point rightTarget() {
		return new Point(1950, 50);
	}

	@Override
	public int deathY() {
		return 500;
	}

	@Override
	public Point leftSpawnPoint() {
		// TODO Auto-generated method stub
		return new Point(900,50);
	}

	@Override
	public Point rightSpawnPoint() {
		// TODO Auto-generated method stub
		return new Point(1100,50);
	}
}