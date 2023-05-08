package dk.sdu.srm.playersystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import dk.sdu.srm.common.bullet.BulletSPI;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {
    private float speed = 120;
    private static final float BULLET_DELAY = 0.7f;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            player.setBulletTimer(player.getBulletTimer() + gameData.getDelta());
            PositionPart positionPart = player.getPart(PositionPart.class);
            positionPart.process(gameData, player);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                if (player.getBulletTimer() >= BULLET_DELAY) {
                    player.setBulletTimer(0);
                    for (BulletSPI bullet : getBulletSPIs()) {
                        world.addEntity(bullet.createBullet(player, gameData, world));
                    }
                }
            }
            updatePlayer(player);
        }
    }

    private void updatePlayer(Entity player) {
        PositionPart positionPart = player.getPart(PositionPart.class);
        float playerx = positionPart.getX();
        float playery = positionPart.getY();

        positionPart.setPreviousPosition(playerx, playery);

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            positionPart.setFacingState(1);
            playery += speed * Gdx.graphics.getDeltaTime();
            player.animationHandler.setCurrentAnimation("up");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            positionPart.setFacingState(-1);
            playery -= speed * Gdx.graphics.getDeltaTime();
            player.animationHandler.setCurrentAnimation("down");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            positionPart.setFacingState(0);
            playerx -= speed * Gdx.graphics.getDeltaTime();
            player.animationHandler.setCurrentAnimation("run");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            positionPart.setFacingState(2);
            playerx += speed * Gdx.graphics.getDeltaTime();
            player.animationHandler.setCurrentAnimation("run");
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (positionPart.getFacingState() == -1 ){
                player.animationHandler.setCurrentAnimation("idle");
            } else if (positionPart.getFacingState() == 1) {
                player.animationHandler.setCurrentAnimation("up_idle");
            } else {
                player.animationHandler.setCurrentAnimation("side_idle");
            }
        }

        positionPart.setPosition(playerx, playery);
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
