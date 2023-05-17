package dk.sdu.srm.mapsystem;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.GameMap;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.mapparts.FloorPart;
import dk.sdu.srm.common.data.mapparts.RoomPart;
import dk.sdu.srm.common.map.Map;
import dk.sdu.srm.common.services.IGamePluginService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;

public class MapPlugin implements IGamePluginService {
    private GameMap map;
    public MapPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        map = initMap(gameData);
        world.addGameMap(map);
    }

    public GameMap initMap(GameData gameData) {
        GameMap map = new Map();
        generateFloors(map);
        populateFloors(map);
        map.setRenderer(new OrthogonalTiledMapRenderer(map.getCurrentFloor().getCurrentRoom().getRoomMap(), 1/32f));
        return map;
    }

    private void generateFloors(GameMap map) {
        map.getFloors().add(new FloorPart(0, 1, 3));
        map.setCurrentFloor(map.getFloors().get(0));
    }

    private void populateFloors(GameMap map) {
        for (FloorPart floor : map.getFloors()) {
            for (int i = 0; i < floor.getNumRooms(); i++) {
                // TODO: Load room maps from some list somewhere :)
                floor.addRoom(new RoomPart(new TmxMapLoader().load("Common/src/main/resources/maps/floor01/room01.tmx")));

            }
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeMap(map);
    }
}
