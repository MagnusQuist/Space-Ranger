package dk.sdu.srm.common.bullet;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;

public interface BulletSPI {

    Entity createBullet(Entity e, GameData gameData, World world);
}
