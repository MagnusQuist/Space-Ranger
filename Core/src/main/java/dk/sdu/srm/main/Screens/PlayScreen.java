package dk.sdu.srm.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.srm.main.SpaceGame;

public class PlayScreen extends ScreenAdapter {
    SpaceGame game;
    Texture texture;
    private OrthographicCamera gamecam;

    public PlayScreen(SpaceGame game){
        this.game = game;
        System.out.println("hello");
        gamecam = new OrthographicCamera(400, 400);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
       // gameport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}