import dk.sdu.srm.aisystem.AIControlSystem;
import dk.sdu.srm.common.ai.AISPI;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;

module AI {
    requires Common;
    requires CommonAI;
    provides IGamePluginService with dk.sdu.srm.aisystem.AIPlugin;
    provides IEntityProcessingService with dk.sdu.srm.aisystem.AIControlSystem;
    provides AISPI with dk.sdu.srm.aisystem.AIControlSystem;
}