package dk.sdu.srm.common.data;

import dk.sdu.srm.common.data.entityparts.AssetsManager;
import com.badlogic.gdx.graphics.Texture;
import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
    // TODO: Create Entity class

    private final UUID ID = UUID.randomUUID();
    private int health;
    private AssetsManager assets;
    private Texture texture;

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAssets(AssetsManager assets) {
        this.assets = assets;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getHealth() {
        return health;
    }

    public AssetsManager getAssets() {
        return assets;
    }

    public Texture getTexture() {
        return texture;
    }

    public String getID() {
        return ID.toString();
    }

}
