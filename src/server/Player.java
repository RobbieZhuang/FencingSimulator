package server;

import java.awt.Point;

import client.gui.Screen;
import mechanics.Hitbox;

public class Player {
    public static final int ATTACK_DURATION = 25;
    public static final int ATTACK_COOLDOWN = 100;
    public static final int RESPAWN_TIME = 30;
    public static final int ATTACK_LEVEL_CHANGE_DELAY = 10;
    private double speed = 5;
    private double x;
	private double y;
	private int ID;
	private int status;
	private Hitbox playerHitbox;
	private boolean facingLeft = true;
	private boolean attacking = false;
	private boolean alive = true;
	private int jumpCounter = 0;
	private int attackCounter = 30;
	private int respawnTimer = 100;
	private int attackLevel = 0;
	private int attackLevelCounter = 0;
	private int totalNumberOfKills = 0;
	private int totalNumberOfDeaths = 0;
	private int team;
	private boolean onGround;
	private int stunCounter;
	private boolean stunned;
	private boolean parrying;
	
	
	public Player(int playerID, int team, Point p) {
		this.x = p.x;
		this.y = p.y;
		this.ID = playerID;
		this.status = 0;
		this.attackLevel = 0;
        this.playerHitbox = new Hitbox(x + (int) (Screen.SPRITE_SIZE * .3), y, (int) (Screen.SPRITE_SIZE * 8 / 13.0), Screen.SPRITE_SIZE);
    }

	public int getTeam(){
		return team;
	}

    public void setTeam(int team) {
        this.team = team;
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
		this.y += -jumpCounter * jumpCounter*0.033;
		if (jumpCounter > 0) {
			this.jumpCounter--;
		}
		updateHitbox();
	}

	private void updateHitbox() {
		this.playerHitbox.setXY(this.x, this.y);
	}

	public Hitbox getHitbox() {
		return playerHitbox;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void dead() {
		attackCounter = ATTACK_COOLDOWN;
		attacking = false;
		alive = false;
		int rand = (int) (Math.random() * 3) + 26;
		status = rand;
		respawnTimer = 100;
		totalNumberOfDeaths++;
	}

	public void revive(Point p) {
		this.alive = true;
		this.x = p.x;
		this.y = p.y;
		this.status = 0;
	}

	public void basicRevive(Point p) {
		if (respawnTimer == 0) {
			this.alive = true;
			revive(p);
		}
	}

	private void iterateRespawnTimer() {
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

	private void setStatus(int status) {
		this.status = status;
	}

	public String getPlayerString() {
		return ID + " " + x + " " + y + " " + status;
	}

	public void stand() {
		if (facingLeft) {
			if (attackLevel == 0){
				setStatus(8);
			} else if (attackLevel == 1){
				setStatus(0);
			} else {
				setStatus(4);
			}
		} else {
			if (attackLevel == 0){
				setStatus(9);
			} else if (attackLevel == 1){
				setStatus(1);
			} else {
				setStatus(5);
			}
		}
	}

	public void walk() {
		if (facingLeft) {
			setStatus(16);
		} else {
			setStatus(18);
		}
	}

	public void jump() {
		if (onGround){
			jumpCounter = 24;
			if (facingLeft) {
				setStatus(20);
			} else {
				setStatus(21);
			}
		}
	}

	public void attack() {
		if (attackCounter >= ATTACK_COOLDOWN - ATTACK_DURATION && attackCounter <= ATTACK_COOLDOWN) {
			if (facingLeft) {
				if (attackLevel == 0){
					setStatus(10);
				} else if (attackLevel == 1){
					setStatus(2);
				} else {
					setStatus(6);
				}
			} else {
				if (attackLevel == 0){
					setStatus(11);
				} else if (attackLevel == 1){
					setStatus(3);
				} else {
					setStatus(7);
				}
			}
			if (attackCounter == ATTACK_COOLDOWN){
				attackCounter--;
			}
			attacking = true;
		}
	}

	private void iterateAttack() {
		if (attacking){
			attack();
		}
		if (attackCounter > 0 && attackCounter < ATTACK_COOLDOWN) {
			attackCounter--;
		} else {
			attackCounter = ATTACK_COOLDOWN;
			attacking = false;
		}

	}

	/**
	 * getTotalNumberOfKills
	 * @return int
	 */
	public int getTotalNumberOfKills() {
		return totalNumberOfKills;
	}

	/**
	 * setTotalNumberOfKills
     * @param
     */
	public void setTotalNumberOfKills(int totalNumberOfKills) {
		this.totalNumberOfKills = totalNumberOfKills;
	}

	public void increaseTotalNumberOfKills(){
		this.totalNumberOfKills++;
	}
	/**
	 * getTotalNumberOfDeaths
     * @return totalNumberOfDeaths
     */
	public int getTotalNumberOfDeaths() {
		return totalNumberOfDeaths;
	}

	/**
	 * setTotalNumberOfDeaths
     * @param totalNumberOfDeaths
     */
	public void setTotalNumberOfDeaths(int totalNumberOfDeaths) {
		this.totalNumberOfDeaths = totalNumberOfDeaths;
	}

	public boolean getOnGround (){
		return onGround;
	}

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

	public void iterateCounters(){
		iterateAttack();
		iterateRespawnTimer();
		iterateStun();
		iterateAttackLevelChange();
	}

	private void iterateAttackLevelChange() {
		if (attackLevelCounter > 0){
			attackLevelCounter --;
		}else {
			attackLevelCounter = ATTACK_LEVEL_CHANGE_DELAY;
		}
	}
	
	public boolean canChangeAttackLevel(){
		return (attackLevelCounter == 0);
	}

	private void iterateStun() {
		if (stunCounter > 0){
			stunCounter --;
			stunned = true;
		} else {
			stunned = false;
		}
		
	}

	public boolean getAttacking() {
		return attacking;
	}

	public void jumpAttack() {
		jumpCounter = 0;
		if (facingLeft){
			setStatus(14);
		}else {
			setStatus(15);
		}
	}

	public void stun() {
		stunCounter = 10;
		if (facingLeft){
			setStatus(22);
		}else {
			setStatus(23);
		}
	}
	
	public boolean getStun(){
		return stunned;
	}

	public void moveSwordUp() {
		attackLevelCounter = ATTACK_LEVEL_CHANGE_DELAY;
		if (attackLevel < 2){
			attackLevel++;
		}			
	}
	public void moveSwordDown() {
		attackLevelCounter = ATTACK_LEVEL_CHANGE_DELAY;
		if (attackLevel >0 ){
			attackLevel--;
		}			
	}

	public void parry(boolean parrying) {
		this.parrying = parrying;
		if (parrying){
			if (facingLeft){
				setStatus(12);
			}else {
				setStatus(13);
			}
		}
	}
	
	public boolean getParrying(){
		return this.parrying;
	}
}
