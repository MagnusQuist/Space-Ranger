package dk.sdu.srm.common.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.util.AnimationHandler;

public class Enemy extends Entity {
    public Enemy() {
        characterAtlas = new TextureAtlas("assets/enemy/animations/enemy.atlas");
        animationHandler = new AnimationHandler();
        animationHandler.add("enemy", new Animation<>(FRAME_TIME, characterAtlas.findRegions("enemy")));
        animationHandler.setCurrentAnimation("enemy");
    }
}
