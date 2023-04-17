import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;

module Map {
    exports dk.sdu.srm.mapsystem;
    requires Common;
    requires com.badlogic.gdx;
    provides IGamePluginService with dk.sdu.srm.mapsystem.MapPlugin;
    provides IEntityProcessingService with dk.sdu.srm.mapsystem.MapControlSystem;
}