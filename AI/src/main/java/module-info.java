import dk.sdu.srm.aisystem.AIPlugin;
import dk.sdu.srm.common.ai.AISPI;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;
import dk.sdu.srm.aisystem.AStar;


module AI {
    requires Common;
    requires CommonAI;
    requires com.badlogic.gdx;
    provides IGamePluginService with AIPlugin;
    provides AISPI with AStar;
}