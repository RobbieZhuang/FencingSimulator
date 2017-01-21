package map;

import java.util.ArrayList;

public class Map {

	private ArrayList<Room> rooms;
	private int size;
	private Room currentRoom;
	private int currentRoomID;
	
	public Map(){
		rooms = new ArrayList<Room>();
		rooms.add(new RoomOutdoors());
		rooms.add(new RoomCastle());
		rooms.add(new RoomOutdoors());
		rooms.add(new RoomCastle());
		rooms.add(new RoomOutdoors());
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
	}
	public Room getCurrentRoom(){
		return currentRoom;
	}
	public int getCurrentRoomID(){
		return currentRoomID;
	}
	public void moveToLeftRoom(){
		int roomPos = getRoom(currentRoom);
		if (roomPos > 0){
			this.currentRoom = this.getRooms().get(roomPos-1);
			currentRoomID = roomPos-1;
		}
	}
	public void moveToRightRoom(){
		int roomPos = getRoom(currentRoom);
		if (roomPos < this.getMapSize()-1){
			this.currentRoom = this.getRooms().get(roomPos+1);
			currentRoomID = roomPos+1;
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
}
