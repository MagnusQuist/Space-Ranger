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

    private static final float COLLISION_DELAY = 1f;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities()){
            for (Entity entity2 : world.getEntities()){

                checkWallCollision(world, entity);

                if (entity.getID().equals(entity2.getID())) {
                    continue;
                } else if (entity instanceof Player && entity2 instanceof Bullet){
                    continue;
                }

                if (this.collision(entity, entity2)){

                    if (entity instanceof Player){
                        entity.setCollisionTimer(entity.getCollisionTimer() + gameData.getDelta());
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

                    if (entity instanceof Bullet && entity2 instanceof Enemy){
                        world.removeEntity(entity);
                        LifePart lifePart = entity2.getPart(LifePart.class);
                        lifePart.setLife(lifePart.getLife() - 1);
                        System.out.println(lifePart.getLife());
                        if (lifePart.getLife() <= 0) {
                            world.removeEntity(entity2);
                        }
                    }

                } else {
                    entity.setCollisionTimer(COLLISION_DELAY);
                }
            }
        }


    }
    private Boolean collision (Entity entity, Entity entity1){
        return entity.getCollision().overlaps(entity1.getCollision());
    }

    private void checkWallCollision(World world, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);

        MapLayer wallObjectsLayer = (MapLayer) world.getGameMap().getCurrentFloor().getCurrentRoom().getRoomMap().getLayers().get("wallObjects");
        MapObjects mapWalls = wallObjectsLayer.getObjects();
        Array<RectangleMapObject> walls = mapWalls.getByType(RectangleMapObject.class);

        for (RectangleMapObject wall : walls) {
            Rectangle wallRect = wall.getRectangle();
            if (entity.getCollision().overlaps(wallRect)) {
                // Bullets that hit walls are removed
                if (entity instanceof Bullet) {
                    // Could maybe trigger a particle effect here
                    world.removeEntity(entity);
                }

                // Entity wall collision
                // Move entity back to previous position
                switch (positionPart.getFacingState()) {
                    case 0 -> positionPart.setPosition(positionPart.getPreviousX() + 1, positionPart.getPreviousY());
                    case 1 -> positionPart.setPosition(positionPart.getPreviousX(), positionPart.getPreviousY() - 1);
                    case 2 -> positionPart.setPosition(positionPart.getPreviousX() - 1, positionPart.getPreviousY());
                    case -1 -> positionPart.setPosition(positionPart.getPreviousX(), positionPart.getPreviousY() + 1);
                }

            }
        }
    }
}
