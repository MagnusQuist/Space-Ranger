package dk.sdu.srm.common.ai;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;

public interface AISPI {
    Entity createAI(Entity e, GameData gameData);
}
