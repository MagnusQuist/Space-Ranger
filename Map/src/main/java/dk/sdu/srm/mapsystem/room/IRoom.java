package dk.sdu.srm.mapsystem.room;

import com.badlogic.gdx.maps.tiled.TiledMap;

public interface IRoom {
    boolean checkRoomCleared();
    TiledMap getRoomMap();
}
