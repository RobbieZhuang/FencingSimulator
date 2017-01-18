package specialEffects;

public class Drop implements Runnable {
	private double x;
	private double y;
	private int z;
	private double xVel;
	private double yVel;
	private double gravity;
	private int width;
	private int height;
	
	private int xMax;
	private int yMax;
	
	private final int minY = -90;
	private final int maxY = -290;
	
	private final int maxZ = 10;
	
	private boolean running;
	
	public Drop (int xMax, int yMax) {
		this.xMax = xMax;
		this.yMax = yMax;
		xVel = 0;
		randomize();
	}
	
	public Drop (int xMax, int yMax, double xVel) {
		this.xMax = xMax;
		this.yMax = yMax;
		this.xVel = xVel;
		randomize();
	}
	
	private void randomize () {
		x = (int)(Math.random()*(xMax+1));
		y = (int)(Math.random()*(maxY-minY)) + minY;
		z = (int)(Math.random()*(maxZ));
		
		gravity = 0.05 + ((Math.random()*0.05)+0.05)*z;
		
		width = (int)(2 + ((Math.random()*.3)*z));
		height = (int)(10 + ((Math.random()*1)*z));
		
		yVel = 0;
	}
	
	public void run() {
		running = true;
		while (running) {
			if (y > yMax + 40) {
				randomize();
			} else if (x > xMax || x < 0) {
				randomize();
			}
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			x += xVel;
			y += yVel;
			
			yVel += gravity;
		}
	}
	
	public void stop () {
		this.running = false;
	}
	
	public int getX () {
		return (int)x;
	}
	
	public int getY () {
		return (int)y;
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}
}