package dk.sdu.srm.common.services;

import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
