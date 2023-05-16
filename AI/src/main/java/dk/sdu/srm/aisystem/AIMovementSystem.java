package dk.sdu.srm.aisystem;

import dk.sdu.srm.common.ai.AI;
import dk.sdu.srm.common.ai.AISPI;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.GameMap;
import dk.sdu.srm.common.data.World;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.srm.common.services.IEntityProcessingService;

public class AIMovementSystem implements IEntityProcessingService, AISPI {
    @Override
    public void process(GameData gameData, World world) {
        // Get the game map and all tiles
        GameMap gameMap = world.getGameMap();
        TiledMapTileLayer floorLayer = gameMap.getFloorLayer();

        // Get the player position


    }

    @Override
    public void AIMovement(GameData gameData) {
        AI ai = new AI();
    }
}
