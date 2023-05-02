import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;

module Player {
    exports dk.sdu.srm.playersystem;
    requires Common;
    requires com.badlogic.gdx;
    requires CommonPlayer;
    provides IGamePluginService with dk.sdu.srm.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.srm.playersystem.PlayerControlSystem;
}