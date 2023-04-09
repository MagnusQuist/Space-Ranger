package dk.sdu.srm.common.data;

import dk.sdu.srm.common.data.entityparts.AssetsManager;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
    // TODO: Create Entity class
    private final UUID ID = UUID.randomUUID();
    private int health;
    private AssetsManager assets;


    public String getID() {
        return ID.toString();
    }

}
