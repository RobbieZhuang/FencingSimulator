package map;

import java.awt.image.BufferedImage;

public class Land {
	private int lX;
	private int lY;
	private int length;
	private int height;

	private int hitBoxDistance;
	
	private BufferedImage texture;
	
	public Land (int lX, int lY, int length, int height, int hitBoxDistance) {
		this.lX = lX;
		this.lY = lY;
		this.length = length;
		this.height = height;
		this.hitBoxDistance = hitBoxDistance;
	}
	
	public boolean isTouching (int pX, int pY) {
		if (pX > lX	- hitBoxDistance && pX < lX + length + hitBoxDistance) {
			if (pY > lY - hitBoxDistance && pY < lY + height + hitBoxDistance) {
				return true;
			}
		}
		return false;
	}
	
	public int getlX() {
		return lX;
	}

	public int getlY() {
		return lY;
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}
}
