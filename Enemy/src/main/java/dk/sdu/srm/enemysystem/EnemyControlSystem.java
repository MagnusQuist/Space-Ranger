package dk.sdu.srm.enemysystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.srm.common.ai.AISPI;
import com.badlogic.gdx.math.Rectangle;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.common.services.IEntityProcessingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    private static final float SPEED = 1 * Gdx.graphics.getDeltaTime();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities(Enemy.class)) {
            PositionPart positionPart = e.getPart(PositionPart.class);
            LifePart lifePart = e.getPart(LifePart.class);
            lifePart.process(gameData, e);
            positionPart.process(gameData, e);
            int tileX = (int) ((positionPart.getX() + (e.getCollision().width / 2)) / (800 / 25));
            int tileY = (int) ((positionPart.getY() + (e.getCollision().height / 2)) / (450 / 15));

            Vector2 targetPosition = null;

            for (Entity player : world.getEntities(Player.class)) {
                PositionPart playerPosition = player.getPart(PositionPart.class);
                targetPosition = new Vector2((int) ((playerPosition.getX() + (player.getCollision().width / 2)) / (800 / 25)), (int) ((playerPosition.getY() + (player.getCollision().height / 2)) / (450 / 15)));
            }
            if (targetPosition != null) {
                if (!targetPosition.equals(e.getTargetPosition())) {
                    e.setTargetPosition(targetPosition);
                    for (AISPI ai : getAISPIs()) {
                        e.setPath(ai.findPath(world.getGameMap().getCurrentFloor().getCurrentRoom().getRoomMask(), tileX, tileY, (int) targetPosition.x, (int) targetPosition.y));
                    }
                }
                updateEnemy(e, e.getPath());
            }
        }
    }

    public void updateEnemy(Entity enemy, ArrayList<Vector2> path) {
        PositionPart positionPart = enemy.getPart(PositionPart.class);
        float enemyx = positionPart.getX();
        float enemyy = positionPart.getY();

        enemy.setCollision(new Rectangle(enemyx, enemyy, 16 * enemy.SPRITE_SIZE, 12 * enemy.SPRITE_SIZE));

        positionPart.setPreviousPosition(enemyx, enemyy);

        if (path != null && !path.isEmpty()) {
            Vector2 next = path.get(0);
            float nextX = next.x * 32;
            float nextY = next.y * 32;

            float dx = nextX - enemyx;
            float dy = nextY - enemyy;

            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance > 0) {
                dx = dx / distance;
                dy = dy / distance;
            }

            float stepX = dx * SPEED / 2;
            float stepY = dy * SPEED / 2;

            if (Math.abs(stepX) > Math.abs(dx)) {
                stepX = dx;
            }

            if (Math.abs(stepY) > Math.abs(dy)) {
                stepY = dy;
            }

            enemyx += stepX;
            enemyy += stepY;

            positionPart.setX(enemyx);
            positionPart.setY(enemyy);

            if (Math.abs(enemyx - nextX) < 0.5f && Math.abs(enemyy - nextY) < 0.5f) {
                path.remove(0);
            }

            if (enemyx > positionPart.getX()) {
                positionPart.setFacingState(1);
            } else {
                positionPart.setFacingState(0);
            }
        }
    }

    private Collection<? extends AISPI> getAISPIs() {
        return ServiceLoader.load(AISPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
