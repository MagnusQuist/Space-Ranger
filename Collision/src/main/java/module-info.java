import dk.sdu.srm.collisionsystem.CollisionSystem;
import dk.sdu.srm.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires com.badlogic.gdx;
    requires CommonBullet;
    requires CommonEnemy;
    requires CommonPlayer;
    provides IPostEntityProcessingService with CollisionSystem;
}