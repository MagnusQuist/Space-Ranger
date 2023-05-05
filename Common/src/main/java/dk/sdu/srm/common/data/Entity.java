package dk.sdu.srm.common.data;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.srm.common.data.entityparts.EntityPart;
import dk.sdu.srm.common.util.AnimationHandler;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private int health;
    private float radius;
    private int armor;
    private int coins;
    private Texture texture;
    private Sprite sprite;
    private Map<Class, EntityPart> parts;
    public float FRAME_TIME = 1 / 15f;
    public TextureAtlas characterAtlas;
    public AnimationHandler animationHandler;

    private float bulletSpeed;

    private boolean canShoot;

    private float attackSpeed;

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
    public int getHealth() {
        return health;
    }
    public void setArmor(int armor) { this.armor = armor; }
    public int getArmor() { return armor; }
    public void setCoins(int coins) { this.coins = coins; }
    public int getCoins() { return coins; }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
    public boolean getCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
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

    public Texture getTexture() {
        return texture;
    }
    public float getRadius() {
        return radius;
    }
    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
    public void setRadius(float r){
        this.radius = r;
    }

    public String getID() {
        return ID.toString();
    }
}
