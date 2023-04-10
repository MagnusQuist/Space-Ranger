import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;

module Player {
    requires Common;
    requires com.badlogic.gdx;
    provides IGamePluginService with dk.sdu.srm.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.srm.playersystem.PlayerControlSystem;
}