package dk.sdu.srm.enemysystem;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.enemy.EnemySpawner;
import dk.sdu.srm.common.enemy.EnemyType;
import dk.sdu.srm.common.services.IGamePluginService;
import dk.sdu.srm.common.util.AnimationHandler;

public class EnemyPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        EnemySpawner spawner = new EnemySpawner();
        spawner.createEnemies(gameData, world, EnemyType.SLIME, 1);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities(Enemy.class)) {
            world.removeEntity(e);
        }
    }
}
