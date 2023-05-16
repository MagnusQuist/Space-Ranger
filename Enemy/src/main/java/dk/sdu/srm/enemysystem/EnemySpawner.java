package dk.sdu.srm.enemysystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.enemy.EnemyType;
import dk.sdu.srm.common.util.AnimationHandler;

import java.util.concurrent.ThreadLocalRandom;

public class EnemySpawner {
    // Level data

    public EnemySpawner() {
    }

    public void createEnemies(GameData gameData, World world, EnemyType type, int amount) {
        for (int i = 0; i < amount; i++) {
            float x = ThreadLocalRandom.current().nextInt(60, 250 + 1);
            float y = ThreadLocalRandom.current().nextInt(60, 250 + 1);

            Entity enemy = new Enemy();
            enemy.characterAtlas = new TextureAtlas("Enemy/src/main/resources/animation/enemy.atlas");
            enemy.animationHandler = new AnimationHandler();
            enemy.animationHandler.add("enemy", new Animation<>(enemy.FRAME_TIME, enemy.characterAtlas.findRegions("enemy")));
            enemy.animationHandler.setCurrentAnimation("enemy");
            enemy.add(new PositionPart(x, y, 0));
            enemy.add(new LifePart(2));

            world.addEntity(enemy);
        }
    }
}
