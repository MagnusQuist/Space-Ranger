package dk.sdu.srm.bulletsystem;

import dk.sdu.srm.common.bullet.Bullet;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities(Bullet.class)) {
            world.removeEntity(e);
        }
    }


}
