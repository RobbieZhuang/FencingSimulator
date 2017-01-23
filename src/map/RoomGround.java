package map;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import specialEffects.Rain;

public class RoomGround extends Room {
	private int RUBBLE_LENGTH = 10;
	private int RUBBLE_HEIGHT = 5;
	
	private LinkedList <Point> rubble;
	private LinkedList <Point> dirt;
	
	private Rain r;
	
	public RoomGround () {
		super (3500, 800);
		r = new Rain(3500, 800, 50, 0);
		r.go();
		rubble = new LinkedList<>();
		dirt = new LinkedList<>();
		LinkedList <Land> terrain = super.getTerrain();
		terrain.add(new Land (0, 0, 3500, 100, 0));
		terrain.add(new Land (0, 700, 1000, 100, 0));
		terrain.add(new Land (750, 700, 2500, 100, 0));
		terrain.add(new Land (0, 500, 300, 200, 0));
		terrain.add(new Land (300, 550, 300, 150, 0));
		terrain.add(new Land (600, 600, 100, 150, 0));
		terrain.add(new Land (700, 650, 100, 150, 0));

		
        for (int a = 0; a < super.getTerrain().size(); a++) {
        	Land l = super.getTerrain().get(a);
        	int nLoops = (int)(Math.random()*l.getLength()/50.0)+1;
        	for (int b = 0; b < nLoops; b++) {
        		int x = (int)(Math.random()*l.getLength()) + l.getlX();
        		int y = l.getlY()-RUBBLE_HEIGHT;
        		rubble.add(new Point(x, y));
        	}
        	nLoops = (int)(Math.random()*l.getLength()*l.getHeight()/2500.0)+1;
        	for (int b = 0; b < nLoops; b++) {
        		int rX = (int)(Math.random()*l.getLength()) + l.getlX();
        		int rY = (int)(Math.random()*l.getHeight()) + l.getlY();
        		dirt.add(new Point(rX, rY));
        	}
        }
        
		generateRespawnPoints();
	}

	@Override
	public void drawRoom(int lX, int tY, Graphics g) {
		g.setColor(Color.black);
		r.drawRain(g, lX, tY);
		g.fillRect(0, 0, super.getLength(), super.getHeight());
		g.setColor(Color.gray);
		for (int a = 0; a < rubble.size(); a++) {
			int x = (int)(rubble.get(a).getX());
			int y = (int)(rubble.get(a).getY());
			g.fillRect(x-lX, y-tY, RUBBLE_LENGTH, RUBBLE_HEIGHT);
		}
		LinkedList <Land> terrain = super.getTerrain();
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, this.getHeight(), new Color (55,41,0), 0, 0, new Color (104,77,3)));
		for (int a = 0; a < terrain.size(); a++) {
			Land l = terrain.get(a);
			g2.fillRect(l.getlX()-lX, l.getlY()-tY, l.getLength(), l.getHeight());
		}
		g.setColor(new Color (51,25,0));
		for (int a = 0; a < dirt.size(); a++) {
			int x = (int)(dirt.get(a).getX());
			int y = (int)(dirt.get(a).getY());
			g.fillRect(x-lX, y-tY, RUBBLE_LENGTH, RUBBLE_HEIGHT);
		}
	}

	@Override
	protected void generateRespawnPoints() {
		super.getRespawn().add(new Point(150, 500));
		super.getRespawn().add(new Point(3400, 100));
		super.getRespawn().add(new Point (1500, 400));
	}

	@Override
	public Point leftTarget() {
		return new Point(50, 50);
	}
	@Override
	public Point rightTarget() {
		return new Point(3450, 50);
	}

	@Override
	public int deathY() {
		return 1000;
	}

}
