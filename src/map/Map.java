package map;

import java.util.ArrayList;

public class Map {

	private ArrayList<Room> rooms;
	private int size;
	private Room currentRoom;
	private int currentRoomID;
	
	public Map(){
		rooms = new ArrayList<Room>();
		rooms.add(new RoomCastle());
		rooms.add(new RoomOutdoors());
		rooms.add(new RoomCastle());
		rooms.add(new RoomOutdoors());
		rooms.add(new RoomCastle());
		currentRoom = rooms.get(2);
		currentRoomID = 2;
		size = rooms.size();
	}
	
	public Room getRoom(int i){
		return rooms.get(i);
	}
	
	public int getRoomID(Room r){
		for (int i = 0; i < rooms.size(); i++) {
			if (r.equals(rooms.get(i))){
				return i;
			}
		}
		return -1;
	}
	public int getNumberOfRooms(){
		return rooms.size();
	}
	public Room getStartingRoom(){
		return rooms.get(2);
	}
	public ArrayList<Room> getRooms(){
		return rooms;
	}
	public int getMapSize(){
		return size;
	}
	public void setCurrentRoom(int i){
		currentRoom = rooms.get(i);
		currentRoomID = i;
	}
	public Room getCurrentRoom(){
		return currentRoom;
	}
	public int getCurrentRoomID(){
		return currentRoomID;
	}
	public void moveToLeftRoom(){
		if (currentRoomID > 0){
			setCurrentRoom(--currentRoomID);
		}
	}
	public void moveToRightRoom(){
		if (currentRoomID < size-1){
			setCurrentRoom(++currentRoomID);
		}
	}
	public int getRoom(Room r){
		for (int i = 0; i < rooms.size(); i++) {
			if (rooms.get(i).equals(r)){
				return i;
			}
		}
		return -1;
	}
	public int getLeftTeamRespawn(){
		return currentRoom.rightTarget();
	}
	public int getRightTeamRespawn(){
		return currentRoom.leftTarget();
	}
}
