package dk.sdu.srm.mapsystem;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.services.IGamePluginService;

public class MapPlugin implements IGamePluginService {

    private Entity map;
    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthographicCamera gamecam;

    //private OrthogonalTiledMapRenderer renderer;

    @Override
    public void start(GameData gameData, World world) {
        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load("assets/tilemap.tmx");
        //renderer = new OrthogonalTiledMapRenderer(tiledMap);
        gamecam.position.set();

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(map);
    }
}
