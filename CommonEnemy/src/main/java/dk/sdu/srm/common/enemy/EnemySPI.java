package dk.sdu.srm.common.enemy;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;

public interface EnemySPI {

   /**
    *   Should maybe have a reference to a map or level.
    */
    Entity createEnemy(GameData gameData);
}
