package map;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

import specialEffects.Lightning;
import specialEffects.Rain;

public class RoomOutdoors extends Room {

	private Rain r;
	private Lightning l;
	
	public RoomOutdoors () {
		super (3000, 700);
		r = new Rain(3000, 700);
		r.go();
		l = new Lightning(3000, 700, 5000, 1000);
		l.go();
		LinkedList <Land> terrain = super.getTerrain();
		terrain.add(new Land (0, 400, 800, 50, 0));
		terrain.add(new Land (900, 350, 200, 50, 0));
		terrain.add(new Land (1200, 300, 800, 50, 0));
		generateRespawnPoints();
	}
	
	@Override
	public void drawRoom(int lX, int tY, Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, super.getLength(), super.getHeight());
		r.drawRain(g, lX, tY);
		LinkedList <Land> terrain = super.getTerrain();
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(new GradientPaint(0, this.getHeight()/2, new Color (0, 51, 0), 0, 0, new Color (0 , 153, 0)));
		for (int a = 0; a < terrain.size(); a++) {
			Land l = terrain.get(a);
			g2.fillRect(l.getlX()-lX, l.getlY()-tY, l.getLength(), l.getHeight());
		}
		l.drawLightning(g);
	}

	@Override
	protected void generateRespawnPoints() {
		super.getRespawn().add(new Point(100, 50));
		super.getRespawn().add(new Point(1100, 50));
		super.getRespawn().add(new Point (500, 50));
	}

	@Override
	public Point leftTarget() {
		return new Point(50,50);
	}
	@Override
	public Point rightTarget() {
		return new Point(1950,50);
	}
}