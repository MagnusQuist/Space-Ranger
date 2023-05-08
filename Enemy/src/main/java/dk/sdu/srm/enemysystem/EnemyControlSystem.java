package dk.sdu.srm.enemysystem;

import com.badlogic.gdx.Gdx;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities(Enemy.class)) {
            PositionPart positionPart = e.getPart(PositionPart.class);
            LifePart lifePart = e.getPart(LifePart.class);

            lifePart.process(gameData, e);
            positionPart.process(gameData, e);
            updateEnemy(e);
        }
    }

    public void updateEnemy(Entity enemy) {
        // TODO: update enemy movement with AI
        PositionPart positionPart = enemy.getPart(PositionPart.class);
        float enemyx = positionPart.getX();
        float enemyy = positionPart.getY();

        positionPart.setPreviousPosition(enemyx, enemyy);

        enemyx += Math.random() * 5 * Gdx.graphics.getDeltaTime();
        enemyy += Math.random() * 5 * Gdx.graphics.getDeltaTime();

        if (enemyx > positionPart.getX()) {
            positionPart.setFacingState(1);
        } else {
            positionPart.setFacingState(0);
        }

        positionPart.setPosition(enemyx, enemyy);
    }
}
