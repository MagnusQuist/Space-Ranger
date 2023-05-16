package dk.sdu.srm.collisionsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import dk.sdu.srm.common.bullet.Bullet;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.common.services.IPostEntityProcessingService;

public class CollisionSystem implements IPostEntityProcessingService {

    private static final float COLLISION_DELAY = 3f;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            checkWallCollision(world, entity);

            if (entity instanceof Enemy) {
                enemyCollision(world, entity, gameData);
            }
            if (entity instanceof Bullet) {
                bulletCollision(world, entity);
            }
        }
    }

    private void enemyCollision(World world, Entity enemy, GameData gameData) {
        PositionPart enemyPositionPart = enemy.getPart(PositionPart.class);
        Rectangle enemyRect = new Rectangle(enemyPositionPart.getX(), enemyPositionPart.getY(), 16 * 1.8f, 12 * 1.8f);

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Player) {
                entity.setCollisionTimer(entity.getCollisionTimer() + gameData.getDelta());
                PositionPart entityPositionPart = entity.getPart(PositionPart.class);
                Rectangle entityRect = new Rectangle(entityPositionPart.getX(), entityPositionPart.getY(), 13 * 1.8f, 21 * 1.8f);

                if (enemyRect.overlaps(entityRect)) {
                    if (entity.getCollisionTimer() >= COLLISION_DELAY) {
                        entity.setCollisionTimer(0);
                        LifePart lifePart = entity.getPart(LifePart.class);
                        lifePart.setLife(lifePart.getLife() - 1);
                        System.out.println(lifePart.getLife());
                        if (lifePart.getLife() <= 0) {
                            world.removeEntity(entity);
                        }
                    }
                }
            }
        }
    }

    private void bulletCollision(World world, Entity bullet) {
        PositionPart bulletPositionPart = bullet.getPart(PositionPart.class);
        Rectangle bulletRect = new Rectangle(bulletPositionPart.getX(), bulletPositionPart.getY(), bullet.animationHandler.getFrame().getRegionWidth(), bullet.animationHandler.getFrame().getRegionHeight());

        for (Entity entity : world.getEntities()) {
            if (entity instanceof Enemy) {
                PositionPart entityPositionPart = entity.getPart(PositionPart.class);
                Rectangle entityRect = new Rectangle(entityPositionPart.getX(), entityPositionPart.getY(), entity.animationHandler.getFrame().getRegionWidth(), entity.animationHandler.getFrame().getRegionHeight());

                if (bulletRect.overlaps(entityRect)) {
                    world.removeEntity(bullet);
                    LifePart lifePart = entity.getPart(LifePart.class);
                    lifePart.setIsHit(true);
                    if (lifePart.isDead()) {
                        world.removeEntity(entity);
                    }
                }
            }
        }
    }

    private void checkWallCollision(World world, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);

        MapLayer wallObjectsLayer = (MapLayer) world.getGameMap().getCurrentFloor().getCurrentRoom().getRoomMap().getLayers().get("wallObjects");
        MapObjects mapWalls = wallObjectsLayer.getObjects();
        Array<RectangleMapObject> walls = mapWalls.getByType(RectangleMapObject.class);

        // Create rectangle from entity animation frame
        Rectangle entityRect = new Rectangle(positionPart.getX(), positionPart.getY(), entity.animationHandler.getFrame().getRegionWidth(), entity.animationHandler.getFrame().getRegionHeight());

        for (RectangleMapObject wall : walls) {
            Rectangle wallRect = wall.getRectangle();
            if (entityRect.overlaps(wallRect)) {
                // Bullets that hit walls are removed
                if (entity instanceof Bullet) {
                    // Could maybe trigger a particle effect here
                    world.removeEntity(entity);
                }

                // Entity wall collision
                // Move entity back to previous position
                positionPart.setPosition(positionPart.getPreviousX(), positionPart.getPreviousY());
            }
        }
    }
}
