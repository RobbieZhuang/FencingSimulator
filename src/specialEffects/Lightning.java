package specialEffects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Lightning implements Runnable {
	private int width;
	private int height;
	private double period;
	private double length;

	private boolean running;

	private long start;

	public Lightning (int width, int height, long period, long length) {
		this.width = width;
		this.height = height;
		this.period = period;
		this.length = length;
	}

	public void go () {
		this.running = true;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (running) {
			start = System.currentTimeMillis();
			try {
				Thread.sleep((long) period);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void drawLightning (Graphics g) {
		long elapsedTime = System.currentTimeMillis() - start;

		if (length > elapsedTime) {
			double transparancy = 1.0 - elapsedTime/length;
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.white);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (transparancy * 1.0f)));
			g2.fillRect(0, 0, width, height);

			System.out.println("sjciddddddddddddddddddddddddddddddd");
		}
	}


}
