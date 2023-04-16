package dk.sdu.srm.playersystem;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.entityparts.AssetsManager;

public class Player extends Entity {

    public Player() {
        AssetsManager.loadTexture();
        AssetsManager.manager.update();
        AssetsManager.manager.finishLoading();

       // Texture texture = AssetsManager.manager.get(AssetsManager.player);

        this.setHealth(100);
        this.setTexture(AssetsManager.manager.get(AssetsManager.player));

        //this.setTexture(new Texture("assets/player/elf_side01_idle.png"));
    }
}
