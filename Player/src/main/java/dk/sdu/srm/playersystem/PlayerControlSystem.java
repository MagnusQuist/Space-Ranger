package dk.sdu.srm.playersystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.services.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService {
    float speed = 100;

    @Override
    public void process(GameData gameData, World world) {
        System.out.println("Jeg er en lille player :))");
        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            positionPart.process(gameData, player);
            updatePlayer(player);
        }
    }

    private void updatePlayer(Entity player) {
        PositionPart positionPart = player.getPart(PositionPart.class);
        float playerx = positionPart.getX();
        float playery = positionPart.getY();

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            playery += speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playery -= speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerx -= speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerx += speed * Gdx.graphics.getDeltaTime();
        }

        positionPart.setPosition(playerx, playery);
    }
}
