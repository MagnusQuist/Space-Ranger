package dk.sdu.srm.collisionsystem;

import dk.sdu.srm.common.bullet.Bullet;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.Enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CollisionSystemTest {
    private CollisionSystem collisionSystem;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setUp() {
        collisionSystem = new CollisionSystem();
        gameData = mock(GameData.class);
        world = mock(World.class);
    }

    @Test
    public void testBulletCollision() {
        Entity enemy = new Enemy();
        PositionPart enemyPositionPart = new PositionPart(0, 0, 0);
        enemy.add(enemyPositionPart);

        // Create a bullet entity
        Entity bullet = new Bullet();
        PositionPart bulletPositionPart = new PositionPart(0, 0, 0);
        bullet.add(bulletPositionPart);

        // Set the bullet position to overlap with the enemy
        bulletPositionPart.setPosition(0, 0);

        // Create a list of entities in the world
        List<Entity> entities = new ArrayList<>();
        entities.add(enemy);
        entities.add(bullet);

        // Mock the world's getEntities() method to return the list of entities
        Mockito.when(world.getEntities()).thenReturn(entities);

        // Verify that the bullet has been removed from the world
        Mockito.verify(world).removeEntity(bullet);

        // Verify that the enemy has been hit
        LifePart enemyLifePart = enemy.getPart(LifePart.class);
        verify(enemyLifePart.isHit());

    }

}