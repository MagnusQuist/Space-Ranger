package dk.sdu.srm.playersystem;

import com.badlogic.gdx.graphics.Texture;
import dk.sdu.srm.common.data.Entity;

public class Player extends Entity {

    public Player() {
        this.setHealth(100);
        this.setTexture(new Texture("assets/player/elf_side01_idle.png"));
    }
}
