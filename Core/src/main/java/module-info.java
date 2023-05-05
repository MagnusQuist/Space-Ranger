import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;
import dk.sdu.srm.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires com.badlogic.gdx;
    requires CommonEnemy;
    requires CommonPlayer;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}