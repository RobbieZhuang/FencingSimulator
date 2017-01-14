package server;

public class GameState {
	
	
	
	private int x;
	private int y;
	private int ID;
	private int status;
	private String gameString;
	
	public GameState (){
		this.x = 0;
		this.y = 0;
		this.ID = 0;
		this.status = 0;
		gameString = ID + " "  + x + " " + y + " " + status;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
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
	public void update(int x, int y, int ID, int status) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.status = status;
		gameString = ID + " "  + x + " " + y + " " + status;

	}
	
	public String getString(){
		return gameString;
	}
}
