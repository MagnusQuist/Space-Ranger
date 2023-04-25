package dk.sdu.srm.mapsystem.floor;

import com.badlogic.gdx.maps.tiled.TiledMap;
import dk.sdu.srm.mapsystem.room.Room;

public interface IFloor {
    boolean checkFloorCleared();
    Room getCurrentRoom();
    void addRoom(Room room);
}
