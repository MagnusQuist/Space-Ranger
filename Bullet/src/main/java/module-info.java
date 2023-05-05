import dk.sdu.srm.common.bullet.BulletSPI;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;

module Bullet {
    requires Common;
    requires CommonBullet;
    requires Player;
    requires com.badlogic.gdx;
    provides IGamePluginService with dk.sdu.srm.bulletsystem.BulletPlugin;
    provides IEntityProcessingService with dk.sdu.srm.bulletsystem.BulletControlSystem;
    provides BulletSPI with dk.sdu.srm.bulletsystem.BulletControlSystem;
}