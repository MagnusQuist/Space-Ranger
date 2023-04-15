package dk.sdu.srm.playersystem;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.srm.common.data.Entity;

public class Player extends Entity {

    public Player() {
        this.setHealth(100);
        this.getAnimationHandler().add("idle", new Animation<>(1 / 15f, new TextureAtlas("assets/player/animations/idle.atlas").findRegions("Idle")));
        this.getAnimationHandler().add("right", new Animation<>(1 / 15f, new TextureAtlas("assets/player/animations/right.atlas").findRegions("right")));
        this.getAnimationHandler().setCurrentAnimation("idle");
    }
}
