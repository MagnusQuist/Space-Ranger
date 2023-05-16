package dk.sdu.srm.common.data.mapparts;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;

public class RoomPart implements MapPart {
    TiledMap map;


    public RoomPart(TiledMap map) {
        this.map = map;
    }

    public TiledMap getRoomMap() {
        return this.map;
    }

    public boolean checkRoomCleared() {
        return false;
    }

    @Override
    public void process(GameData gameData, World world) {

    }
}
