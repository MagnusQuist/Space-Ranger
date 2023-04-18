package dk.sdu.srm.enemysystem;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.enemy.EnemySPI;
import dk.sdu.srm.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService, EnemySPI {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities(Enemy.class)) {

        }
    }

    @Override
    public Entity createEnemy(GameData gameData) {
        return null;
    }
}
