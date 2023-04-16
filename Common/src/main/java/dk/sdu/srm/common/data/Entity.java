package dk.sdu.srm.common.data;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dk.sdu.srm.common.data.entityparts.AssetsManager;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.srm.common.data.entityparts.EntityPart;
import dk.sdu.srm.common.util.AnimationHandler;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private int health;
    private AssetsManager assets;
    private Texture texture;
    private Map<Class, EntityPart> parts;

    public float FRAME_TIME = 1 / 15f;
    public TextureAtlas characterAtlas;
    public AnimationHandler animationHandler;

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
