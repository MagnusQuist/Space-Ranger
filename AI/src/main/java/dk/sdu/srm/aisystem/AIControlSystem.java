package dk.sdu.srm.aisystem;

import dk.sdu.srm.common.ai.AISPI;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.services.IEntityProcessingService;

public class AIControlSystem implements IEntityProcessingService, AISPI {
    @Override
    public void process(GameData gameData, World world) {

    }

    @Override
    public Entity createAI(Entity e, GameData gameData) {
        return null;
    }
}
