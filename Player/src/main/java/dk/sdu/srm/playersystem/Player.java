package dk.sdu.srm.playersystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.srm.common.data.entityparts.AssetsManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.util.AnimationHandler;

public class Player extends Entity {

    public Player() {
        AssetsManager.loadTexture();
        AssetsManager.manager.update();
        AssetsManager.manager.finishLoading();

       // Texture texture = AssetsManager.manager.get(AssetsManager.player);

        this.setHealth(100);
        this.setTexture(AssetsManager.manager.get(AssetsManager.player));

        characterAtlas = new TextureAtlas("assets/player/animations/player.atlas");

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
