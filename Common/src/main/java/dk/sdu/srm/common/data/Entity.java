package dk.sdu.srm.common.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import dk.sdu.srm.common.data.entityparts.EntityPart;
import dk.sdu.srm.common.util.AnimationHandler;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();
    private int health;
    private float radius;
    private Texture texture;
    private Sprite sprite;
    private Map<Class, EntityPart> parts;
    public float FRAME_TIME = 1 / 4f;
    public float SPRITE_SIZE = 1.8f;
    public TextureAtlas characterAtlas;
    public AnimationHandler animationHandler;
    private float bulletTimer;
    private float collisionTimer;
    private float bulletSpeed;
    private Rectangle collision;
    private ArrayList<Vector2> path;
    private Vector2 targetPosition;

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
    public void setRadius(float r){
        this.radius = r;
    }
    public String getID() {
        return ID.toString();
    }
    public void setBulletTimer(float bulletTimer) {
        this.bulletTimer = bulletTimer;
    }
    public float getBulletTimer() {
        return bulletTimer;
    }
    public void setCollisionTimer(float collisionTimer){
        this.collisionTimer = collisionTimer;
    }
    public float getCollisionTimer(){
        return collisionTimer;
    }
    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
    public float getBulletSpeed() {
        return bulletSpeed;
    }

    public void setCollision(Rectangle collision){
        this.collision = collision;
    }
    public Rectangle getCollision(){
        return collision;
    }
    public void setPath(ArrayList<Vector2> path){
        this.path = path;
    }
    public ArrayList<Vector2> getPath(){
        return path;
    }
    public void setTargetPosition(Vector2 targetPosition){
        this.targetPosition = targetPosition;
    }
    public Vector2 getTargetPosition(){
        return targetPosition;
    }
}
