package dk.sdu.srm.playersystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {
    private Entity player;
    Texture texture;

    public PlayerPlugin() {
    }
    @Override
    public void start(GameData gameData, World world) {
        System.out.println("alo");
        texture = new Texture(Gdx.files.internal("assets/Tandbørste.png"));
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

    private Entity createPlayer(GameData gameData){

        Entity playerMan = new Player();


        return playerMan;
    }
}
