package dk.sdu.srm.playersystem;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.util.AnimationHandler;


public class Player extends Entity {

    public Player() {
        this.setHealth(3);
        this.setArmor(60);
        this.setCoins(30);
        this.setCanShoot(true);

        characterAtlas = new TextureAtlas("Player/src/main/resources/movement/player.atlas");

        animationHandler = new AnimationHandler();
        animationHandler.add("idle", new Animation<>(FRAME_TIME, characterAtlas.findRegions("idle")));
        animationHandler.add("side_idle", new Animation<>(FRAME_TIME, characterAtlas.findRegions("side_idle")));
        animationHandler.add("run", new Animation<>(FRAME_TIME, characterAtlas.findRegions("run")));
        animationHandler.add("up", new Animation<>(FRAME_TIME, characterAtlas.findRegions("up")));
        animationHandler.add("down", new Animation<>(FRAME_TIME, characterAtlas.findRegions("down")));
        animationHandler.add("up_idle", new Animation<>(FRAME_TIME, characterAtlas.findRegions("up_idle")));
        animationHandler.setCurrentAnimation("idle");
    }
}
