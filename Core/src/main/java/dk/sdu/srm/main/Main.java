package dk.sdu.srm.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Space Ranger Man");
        config.setWindowSizeLimits(1000,600,1000,600);

        new Lwjgl3Application(new Game(), config);
    }
}
