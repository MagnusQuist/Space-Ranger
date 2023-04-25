package dk.sdu.srm.mapsystem.room;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class Room implements IRoom {
    TiledMap map;

    public Room(TiledMap map) {
        this.map = map;
    }

    @Override
    public boolean checkRoomCleared() {
        return false;
    }

    @Override
    public TiledMap getRoomMap() {
        return map;
    }
}
