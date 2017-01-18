package map;

import java.awt.image.BufferedImage;

import mechanics.Hitbox;

public class Land {
	private int lX;
	private int lY;
	private int length;
	private int height;

	private Hitbox hitbox;
	
	private BufferedImage texture;
	
	public Land (int lX, int lY, int length, int height, int hitBoxDistance) {
		this.lX = lX;
		this.lY = lY;
		this.length = length;
		this.height = height;
		hitbox = new Hitbox(lX - hitBoxDistance, lY - hitBoxDistance, length + hitBoxDistance*2, height + hitBoxDistance*2);
	}
	
	public boolean collidesWith (Hitbox h) {
		return hitbox.collidesWith(h);
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

	public Hitbox getHitbox() {
		return hitbox;
	}

	public BufferedImage getTexture() {
		return texture;
	}
	
	
}