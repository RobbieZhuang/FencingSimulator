package server;

import mechanics.Hitbox;

public class Player {
	private double speed = 5;
	private double x;
	private double y;
	private int ID;
	private int status;
	private Hitbox hitbox;
	private boolean facingLeft = true;
	private boolean attacking = false;
	private boolean alive = true;
	private int jumpCounter = 0;
	private int attackCounter = 30;

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

	void faceLeft(){
		this.facingLeft = true;
	}
	void faceRight(){
		this.facingLeft = false;
	}
	public boolean isFacingLeft(){
		return facingLeft;
	}
	public boolean isFacingRight(){
		return !facingLeft;
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

	public void iterateJump() {
		this.y += -jumpCounter*jumpCounter;
		if (jumpCounter > 0){
			this.jumpCounter --;
		}
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
	public boolean isAlive(){
		return this.alive;
	}
	public void dead(){
		this.alive = false;
		int rand = (int)Math.random()*3 + 8;
		status = rand;
		
	}
	public void revive(int x, int y){
		this.alive = true;
		this.x = x; this.y = y;
		this.status = 0;
	}
	public void basicRevive(){
		this.alive = true;
		this.status = 0;
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
	public void stand(){
		if (facingLeft){
			this.status = 0;
		} else {
			this.status = 1;
		}
	}

	public void walk(){
		if (facingLeft){
			this.status = 4;
		} else {
			this.status = 6;
		}
	}

	public void jump(){
		jumpCounter = 4;
		if (facingLeft){
			this.status = 8;
		} else {
			this.status = 9;
		}
	}

	public void attack() {
		if (attackCounter >= 15 && attackCounter <= 30){
			if (facingLeft){
				moveLeft();
				moveLeft();
				moveLeft();
				moveRight();
				moveRight();
				moveRight();
				this.status = 2;
			} else {
				moveRight();
				moveRight();
				moveRight();
				moveLeft();
				moveLeft();
				moveLeft();
				this.status = 3;
			}
			attackCounter--;
			attacking = true;
		}
	}

	public void iterateAttack() {
		if (attacking){
			attack();
		}
		if (attackCounter > 0 && attackCounter < 30){
			attackCounter --;
		} else{
			attackCounter = 30;
			attacking = false;
		}
		
	}

	public boolean getAttacking() {
		return attacking;
	}
}
