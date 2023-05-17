import dk.sdu.srm.enemysystem.EnemyControlSystem;
import dk.sdu.srm.enemysystem.EnemyPlugin;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;
import dk.sdu.srm.common.ai.AISPI;

module Enemy {
    requires Common;
    requires CommonEnemy;
    requires CommonAI;
    requires com.badlogic.gdx;
    provides IEntityProcessingService with EnemyControlSystem;
    provides IGamePluginService with EnemyPlugin;
    uses AISPI;
}