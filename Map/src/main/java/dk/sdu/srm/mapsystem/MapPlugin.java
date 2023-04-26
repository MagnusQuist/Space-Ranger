package dk.sdu.srm.mapsystem;

import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.GameMap;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.services.IGamePluginService;

public class MapPlugin implements IGamePluginService {
    private GameMap map;
    public MapPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        System.out.println("MapPlugin started");
        map = new Map();
        world.addGameMap(map);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeMap(map);
    }
}
