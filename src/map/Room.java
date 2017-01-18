package map;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public abstract class Room {
	private int length;
	private int height;
	private LinkedList <Land> terrain;
	private LinkedList <Point> respawn;

	public Room (int length, int height) {
		this.length = length;
		this.height = height;
		terrain = new LinkedList<>();
		respawn = new LinkedList<>();
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	public LinkedList <Land> getTerrain() {
		return terrain;
	}

	public LinkedList <Point> getRespawn () {
		return respawn;
	}

	public Point getNearestLeftRespawn (int pX) {
		boolean respawnFound = false;
		Point p = new Point (-99999999,9999999);
		for (int a = 0; a < respawn.size(); a++) {
			Point rP = respawn.get(a);
			if (rP.getX() < pX) {
				if (rP.getX() > p.getX()) {
					p = rP;
					respawnFound = true;
				}
			}
		}
		if (respawnFound) {
			return p;
		}
		return null;
	}

	public Point getNearestRightRespawn (int pX) {
		boolean respawnFound = false;
		Point p = new Point (99999999,9999999);
		for (int a = 0; a < respawn.size(); a++) {
			Point rP = respawn.get(a);
			if (rP.getX() > pX) {
				if (rP.getX() < p.getX()) {
					p = rP;
					respawnFound = true;
				}
			}
		}
		if (respawnFound) {
			return p;
		}
		return null;
	}

	public Point getNearestRespawn (int pX) {
		boolean respawnFound = false;
		Point p = new Point (99999999,9999999);
		for (int a = 0; a < respawn.size(); a++) {
			Point rP = respawn.get(a);
			if (Math.abs(rP.getX()-pX) < Math.abs(p.getX()-pX)) {
				p = rP;
				respawnFound = true;
			}
		}
		if (respawnFound) {
			return p;
		}
		return null;
	}

	public abstract void drawRoom (int lX, int tY, Graphics g);
	protected abstract void generateRespawnPoints ();


}