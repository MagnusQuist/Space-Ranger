package dk.sdu.srm.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


public class Main {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(SpaceGame.TITLE);
        config.setWindowedMode(SpaceGame.WIDTH, SpaceGame.HEIGHT);
        config.setResizable(false);
        new Lwjgl3Application(new SpaceGame(), config);
    }
}
