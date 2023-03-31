package dk.sdu.srm.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;

    @Override
    public void create() {
        cam = new OrthographicCamera();
        cam.update();
    }

    @Override
    public void render() {

    }

    @Override
    public void resize(int i, int i1) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
