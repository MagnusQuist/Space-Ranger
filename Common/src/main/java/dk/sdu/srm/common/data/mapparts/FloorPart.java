package dk.sdu.srm.common.data.mapparts;

import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;

import java.util.ArrayList;

public class FloorPart implements MapPart {
    private int floorNumber;
    private int currentRoom = 0;
    private int numEnemies;
    private int numRooms;
    private int numClearedRooms;
    private ArrayList<RoomPart> rooms = new ArrayList<>();

    public FloorPart(int floorNumber, int numRooms, int numEnemies) {
        this.floorNumber = floorNumber;
        this.numRooms = numRooms;
        this.numEnemies = numEnemies;
        this.numClearedRooms = 0;
    }

    public void addRoom(RoomPart room) {
        rooms.add(room);
    }

    public RoomPart getCurrentRoom() {
        return rooms.get(currentRoom);
    }

    public boolean checkFloorCleared() {
        return numClearedRooms == numRooms;
    }

    public boolean checkRoomCleared() {
        return rooms.get(currentRoom).checkRoomCleared();
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public int getNumClearedRooms() {
        return numClearedRooms;
    }

    public void setNumClearedRooms(int numClearedRooms) {
        this.numClearedRooms = numClearedRooms;
    }

    public int getNumEnemies() {
        return numEnemies;
    }

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    public ArrayList<RoomPart> getRooms() {
        return rooms;
    }

    @Override
    public void process(GameData gameData, World world) {

    }
}
