package dk.sdu.srm.common.enemy;

import com.badlogic.gdx.Gdx;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.PositionPart;

import java.util.concurrent.ThreadLocalRandom;

public class EnemySpawner {
    // Level data

    public EnemySpawner() {
    }

    public void createEnemies(GameData gameData, World world, EnemyType type, int amount) {
        for (int i = 0; i < amount; i++) {
            float x = ThreadLocalRandom.current().nextFloat(0, Gdx.graphics.getWidth() / 2 - 1);
            float y = ThreadLocalRandom.current().nextFloat(0, Gdx.graphics.getHeight() / 2 - 1);

            Entity enemy = new Enemy();
            enemy.add(new PositionPart(x, y));


            world.addEntity(enemy);
        }
    }
}
