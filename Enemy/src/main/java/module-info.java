import dk.sdu.srm.enemysystem.EnemyControlSystem;
import dk.sdu.srm.enemysystem.EnemyPlugin;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonEnemy;
    requires CommonAI;
    provides IEntityProcessingService with EnemyControlSystem;
    provides IGamePluginService with EnemyPlugin;
}