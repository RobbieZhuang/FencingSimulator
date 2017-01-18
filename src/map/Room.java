package map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Room {
	private int length;
	private int height;
	private LinkedList <Land> terrain;
	private Color background;
	
	public Room (int length, int height) {
		this.length = length;
		this.height = height;
		terrain = new LinkedList<>();
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	public LinkedList<Land> getTerrain() {
		return terrain;
	}
	
	public abstract void drawRoom (int lX, int tY, Graphics g);
	
}