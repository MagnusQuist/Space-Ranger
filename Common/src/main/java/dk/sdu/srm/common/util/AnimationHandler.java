package dk.sdu.srm.common.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Objects;

public class AnimationHandler {

    private float timer = 0;
    private boolean looping = true;
    private String currentAnimation;
    private final HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();

    public void add(String name, Animation<TextureRegion> animation) {
        animations.put(name, animation);
    }

    public void setCurrentAnimation(String name) {
        if (Objects.equals(currentAnimation, name)) return;
        assert (animations.containsKey(name)) : "Animation " + name + " does not exist";
        currentAnimation = name;
        timer = 0;
        looping = true;
    }

    public void setCurrentAnimation(String name, boolean looping) {
        setCurrentAnimation(name);
        this.looping = looping;
    }

    public void setAnimationDuration(long duration) {
        animations.get(currentAnimation).setFrameDuration(duration / ((float) animations.get(currentAnimation).getKeyFrames().length) * 1000);
    }

    public boolean isCurrentAnimation(String name) {
        return Objects.equals(currentAnimation, name);
    }

    public boolean isFinished() {
        return animations.get(currentAnimation).isAnimationFinished(timer);
    }

    public int frameIndex() {
        return animations.get(currentAnimation).getKeyFrameIndex(timer);
    }

    public TextureRegion getFrame() {
        timer += Gdx.graphics.getDeltaTime();
        return animations.get(currentAnimation).getKeyFrame(timer, looping);
    }
}
