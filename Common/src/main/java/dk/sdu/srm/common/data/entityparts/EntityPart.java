package dk.sdu.srm.common.data.entityparts;

import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;

public interface EntityPart {
    void process(GameData gameData, Entity entity);
}
