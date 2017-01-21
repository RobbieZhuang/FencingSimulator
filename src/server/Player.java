package server;

import client.DankTings;
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
	private int respawnTimer = 100;
	private int totalNumberOfKills = 0;
	private int totalNumberOfDeaths = 0;

	/**
	 * getTotalNumberOfKills
	 * @return int
	 */
	public int getTotalNumberOfKills() {
		return totalNumberOfKills;
	}
	
	/**
	 * setTotalNumberOfKills
	 * @param int 
	 */
	public void setTotalNumberOfKills(int totalNumberOfKills) {
		this.totalNumberOfKills = totalNumberOfKills;
	}
	
	public void increaseTotalNumberOfKills(){
		this.totalNumberOfKills++;
	}
	/**
	 * getTotalNumberOfDeaths
	 * @return int
	 */
	public int getTotalNumberOfDeaths() {
		return totalNumberOfDeaths;
	}
	
	/**
	 * setTotalNumberOfDeaths
	 * @param int 
	 */
	public void setTotalNumberOfDeaths(int totalNumberOfDeaths) {
		this.totalNumberOfDeaths = totalNumberOfDeaths;
	}

	public Player(int playerID) {
		this.x = 150;
		this.y = 150;
		this.ID = playerID;
		this.status = 0;
		this.hitbox = new Hitbox(x+(int)(DankTings.SPRITE_SIZE*.3), y, (int)(DankTings.SPRITE_SIZE*8/13.0), DankTings.SPRITE_SIZE);
	}

	void faceLeft() {
		this.facingLeft = true;
	}

	void faceRight() {
		this.facingLeft = false;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}

	public boolean isFacingRight() {
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
		this.y += -jumpCounter * jumpCounter;
		if (jumpCounter > 0) {
			this.jumpCounter--;
		}
		updateHitbox();
	}

	private void updateHitbox() {
		this.hitbox.setXY(this.x, this.y);
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void dead() {
		this.alive = false;
		int rand = (int) (Math.random() * 3) + 10;
		status = rand;
		respawnTimer = 100;
		totalNumberOfDeaths++;
	}

	public void revive(int x, int y) {
		this.alive = true;
		this.x = x;
		this.y = y;
		this.status = 0;
	}

	public void basicRevive() {
		if (respawnTimer == 0) {
			this.alive = true;
			this.status = 0;
		}
	}

	public void iterateRespawnTimer() {
		if (respawnTimer > 0) {
			respawnTimer--;
		}
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
		if (status >= 7) {
			status = 0;
		} else {
			status++;
		}
	}

	public void stand() {
		if (facingLeft) {
			this.status = 0;
		} else {
			this.status = 1;
		}
	}

	public void walk() {
		if (facingLeft) {
			this.status = 4;
		} else {
			this.status = 6;
		}
	}

	public void jump() {
		jumpCounter = 4;
		if (facingLeft) {
			this.status = 8;
		} else {
			this.status = 9;
		}
	}

	public void attack() {
		if (attackCounter >= 15 && attackCounter <= 30) {
			if (facingLeft) {
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
		if (attacking) {
			attack();
		}
		if (attackCounter > 0 && attackCounter < 30) {
			attackCounter--;
		} else {
			attackCounter = 30;
			attacking = false;
		}

	}

	public boolean getAttacking() {
		return attacking;
	}
}
