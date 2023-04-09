package dk.sdu.srm.common.services;

import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;

public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
