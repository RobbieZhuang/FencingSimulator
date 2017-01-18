package specialEffects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Rain {
	private static final int defaultNumDrops = 800;
	private static Color dropColor = new Color(0,51,103);
	private ArrayList <Drop> drops;
	private boolean running;

	public Rain (int width, int height, int numDrops, int wind) {
		drops = new ArrayList<>();

		for (int a = 0; a < numDrops; a++) {
			Drop d = new Drop(width, height, wind);
			drops.add(d);
		}
	}

	public Rain (int width, int height, int wind) {
		drops = new ArrayList<>();

		for (int a = 0; a < defaultNumDrops; a++) {
			Drop d = new Drop(width, height, wind);
			drops.add(d);
		}
	}

	public Rain (int width, int height) {
		drops = new ArrayList<>();
		for (int a = 0; a < defaultNumDrops; a++) {
			Drop d = new Drop(width, height);
			drops.add(d);
		}
	}

	public void go () {
		running = true;
		for (int a = 0; a < drops.size(); a++) {
			Drop d = drops.get(a);
			Thread t = new Thread(d);
			t.start();
		}
	}

	public void drawRain (Graphics g) {
		// TODO Auto-generated method stub
		if (!running) {
			return;
		}
		g.setColor(dropColor);
		for (Drop d: drops) {
			g.fillRect(d.getX(), d.getY(), d.getWidth(), d.getHeight());
		}
	}

	public void stop () {
		for (int a = 0; a < drops.size(); a++) {
			Drop d = drops.get(a);
			d.stop();
		}
		running = false;
	}
}
