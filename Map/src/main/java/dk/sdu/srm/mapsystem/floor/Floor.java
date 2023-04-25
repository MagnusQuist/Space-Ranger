package dk.sdu.srm.mapsystem.floor;

import com.badlogic.gdx.maps.tiled.TiledMap;
import dk.sdu.srm.mapsystem.room.Room;

import java.util.ArrayList;

public class Floor implements IFloor {
    private int floorNumber;
    private int currentRoom;
    private int numEnemies;
    private int numRooms;
    private int numClearedRooms;
    private ArrayList<Room> rooms = new ArrayList<>();
    public Floor(int floorNumber, int numEnemies, int numRooms) {
        this.floorNumber = floorNumber;
        this.currentRoom = 0;
        this.numEnemies = numEnemies;
        this.numRooms = numRooms;
        this.numClearedRooms = 0;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    @Override
    public boolean checkFloorCleared() {
        return numClearedRooms == numRooms;
    }

    @Override
    public Room getCurrentRoom() {
        return rooms.get(currentRoom);
    }

    @Override
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getNumEnemies() {
        return numEnemies;
    }

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public int getNumClearedRooms() {
        return numClearedRooms;
    }

    public void setNumClearedRooms(int numClearedRooms) {
        this.numClearedRooms = numClearedRooms;
    }
}
