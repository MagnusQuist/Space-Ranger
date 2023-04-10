package dk.sdu.srm.common.bullet;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;

public interface BulletSPI {

    Entity createBullet(Entity e, GameData gameData);
}
