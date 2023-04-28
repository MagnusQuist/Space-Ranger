package dk.sdu.srm.common.bullet;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.entityparts.AssetsManager;
import dk.sdu.srm.common.util.AnimationHandler;

public class Bullet extends Entity {

    public Bullet() {
        AssetsManager.loadTexture();
        AssetsManager.manager.update();
        AssetsManager.manager.finishLoading();
        TextureAtlas texture = AssetsManager.manager.get(AssetsManager.PLAYER);
        this.setHealth(100);

        characterAtlas = texture;


        animationHandler = new AnimationHandler();
        animationHandler.add("idle", new Animation<>(FRAME_TIME, characterAtlas.findRegions("idle")));
    }


}
