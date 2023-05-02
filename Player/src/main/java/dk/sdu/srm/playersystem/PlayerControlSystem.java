package dk.sdu.srm.playersystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import dk.sdu.srm.common.bullet.BulletSPI;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.util.SPILocator;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {
    private float speed = 100;

    float delta = Gdx.graphics.getDeltaTime();

    //private float fireRate = 1;
    //private float fireDelay = 0;


    private boolean firingOnCooldown = false;

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    @Override
    public void process(GameData gameData, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            positionPart.process(gameData, player);


            /*if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !firingOnCooldown) {
                firingOnCooldown = true;

                int playerFacingState = positionPart.getFacingState();
                for (BulletSPI bullet : getBulletSPIs()) {
                    world.addEntity(bullet.createBullet(player, gameData, world));
                }

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        firingOnCooldown = false;
                    }
                }, 0.2f);
            }*/
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                int playerFacingState = positionPart.getFacingState();
                for (BulletSPI bullet : getBulletSPIs()) {
                    world.addEntity(bullet.createBullet(player, gameData, world));
                }
            }


            updatePlayer(player);

        }
    }


    private void updatePlayer(Entity player) {
        PositionPart positionPart = player.getPart(PositionPart.class);
        float playerx = positionPart.getX();
        float playery = positionPart.getY();

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
}
