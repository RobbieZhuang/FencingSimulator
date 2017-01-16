package server;

public class Player {
	private double speed = 1;
	private double x;
	private double y;
	private int ID;
	private int status;
	
	public Player(int playerID) {
		this.x = 0;
		this.y = 0;
		this.ID = playerID;
		this.status = 0;
	}
	
	public void moveUp() {
		this.y += -speed;
	}
	public void moveLeft() {
		this.x += -speed;
	}
	public void moveRight() {
		this.x += speed;
	}
	public void moveDown() {
		this.y += speed;
	}

	public double getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public String getPlayerString() {
		return ID + " " + x + " " + y + " " + status;
	}


	
}
