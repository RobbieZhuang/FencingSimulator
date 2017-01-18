package server;

import mechanics.Hitbox;

public class Player {
	private double speed = 5;
	private double x;
	private double y;
	private int ID;
	private int status;
	private Hitbox hitbox;

	public Player(int playerID) {
		if (playerID == 1){
			this.x = 150;
			this.y = 150;
			this.ID = playerID;
			this.status = 0;
			this.hitbox = new Hitbox(x,y,x+135,y+135);
		}
		else{
			this.x = 0;
			this.y = 0;
			this.ID = playerID;
			this.status = 0;
			this.hitbox = new Hitbox(x,y,x+135,y+135);
		}
	}

	public void moveUp() {
		this.y += -speed;
		updateHitbox();
	}
	public void moveLeft() {
		this.x += -speed;
		updateHitbox();
	}
	public void moveRight() {
		this.x += speed;
		updateHitbox();
	}
	public void moveDown() {
		this.y += speed;
		updateHitbox();
	}
	private void updateHitbox(){
		this.hitbox.settY(this.y);
		this.hitbox.setlX(this.x);
		this.hitbox.setRectForPlayer((int)this.x,(int)this.y);
	}
	public Hitbox getHitbox(){
		return hitbox;
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

	public void changeStatus() {
		if (status >= 7){
			status = 0;
		}else {
			status++;
		}
	}
}
