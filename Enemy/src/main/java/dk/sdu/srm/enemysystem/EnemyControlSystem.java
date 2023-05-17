package dk.sdu.srm.enemysystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.srm.common.ai.AISPI;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.services.IEntityProcessingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    private static final float SPEED = 80;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities(Enemy.class)) {
            PositionPart positionPart = e.getPart(PositionPart.class);
            LifePart lifePart = e.getPart(LifePart.class);
            ArrayList<Vector2> path = null;
            lifePart.process(gameData, e);
            positionPart.process(gameData, e);
            int tileX = (int) ((positionPart.getX() + (e.getC / 2)) / (800 / 25));
            int tileY = (int) ((positionPart.getY() + (frame.getRegionHeight() / 2)) / (450 / 15));
            for (AISPI ai : getAISPIs()) {
                path = ai.findPath(world.getGameMap().getCurrentFloor().getCurrentRoom().getRoomMask(), (int) positionPart.getX(), (int) positionPart.getY());
            }
            //System.out.println(path);
            //print roommask to console
            System.out.println(world.getGameMap().getCurrentFloor().getCurrentRoom().toString());
            updateEnemy(e,path);
        }
    }

    public void updateEnemy(Entity enemy, ArrayList<Vector2> path) {
        // TODO: update enemy movement with AI
        PositionPart positionPart = enemy.getPart(PositionPart.class);
        float enemyx = positionPart.getX();
        float enemyy = positionPart.getY();

        positionPart.setPreviousPosition(enemyx, enemyy);

        if (path != null && path.size() > 0) {
            Vector2 nextPos = path.get(0);
            enemyx = nextPos.x;
            enemyy = nextPos.y;
            path.remove(0);
        }

        if (enemyx > positionPart.getX()) {
            positionPart.setFacingState(1);
        } else {
            positionPart.setFacingState(0);
        }

        positionPart.setPosition(enemyx, enemyy);
    }

    private Collection<? extends AISPI> getAISPIs() {
        return ServiceLoader.load(AISPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
