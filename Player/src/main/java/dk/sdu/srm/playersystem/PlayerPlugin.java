package dk.sdu.srm.playersystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.common.services.IGamePluginService;
import dk.sdu.srm.common.util.AnimationHandler;

public class PlayerPlugin implements IGamePluginService {
    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData){
        float x = 50;
        float y = 50;

        Entity player = new Player();
        player.add(new PositionPart(x, y, 0));

        player.setHealth(5);

        player.animationHandler = new AnimationHandler();
        player.characterAtlas = new TextureAtlas("Player/src/main/resources/movement/player.atlas");

        float FRAME_TIME = player.FRAME_TIME;
        TextureAtlas characterAtlas = player.characterAtlas;

        player.animationHandler.add("idle", new Animation<>(FRAME_TIME, characterAtlas.findRegions("idle")));
        player.animationHandler.add("side_idle", new Animation<>(FRAME_TIME, characterAtlas.findRegions("side_idle")));
        player.animationHandler.add("run", new Animation<>(FRAME_TIME, characterAtlas.findRegions("run")));
        player.animationHandler.add("up", new Animation<>(FRAME_TIME, characterAtlas.findRegions("up")));
        player.animationHandler.add("down", new Animation<>(FRAME_TIME, characterAtlas.findRegions("down")));
        player.animationHandler.add("up_idle", new Animation<>(FRAME_TIME, characterAtlas.findRegions("up_idle")));
        player.animationHandler.setCurrentAnimation("idle");

        player.add(new PositionPart(x, y, 0));
        player.add(new LifePart(5));

        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }
}
