package dk.sdu.srm.common.data.mapparts;

import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;

public interface MapPart {
    void process(GameData gameData, World world);
}
