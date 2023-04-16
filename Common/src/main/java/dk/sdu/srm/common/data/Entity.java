package dk.sdu.srm.common.data;

import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.srm.common.data.entityparts.AssetsManager;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.srm.common.data.entityparts.EntityPart;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private int health;
    private AssetsManager assets;
    private Texture texture;

    private Sprite sprite;
    private Map<Class, EntityPart> parts;

    private float x;
    private float y;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAssets(AssetsManager assets) {
        this.assets = assets;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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
