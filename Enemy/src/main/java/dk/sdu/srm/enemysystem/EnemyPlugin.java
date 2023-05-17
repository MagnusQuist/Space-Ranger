package dk.sdu.srm.enemysystem;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.enemy.EnemyType;
import dk.sdu.srm.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        // This should be done by the map component
        EnemySpawner spawner = new EnemySpawner();
        spawner.createEnemies(gameData, world, EnemyType.ALIEN, 1);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities(Enemy.class)) {
            world.removeEntity(e);
        }
    }
}
