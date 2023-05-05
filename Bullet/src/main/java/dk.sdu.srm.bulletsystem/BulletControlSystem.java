package dk.sdu.srm.bulletsystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;
import dk.sdu.srm.common.bullet.Bullet;
import dk.sdu.srm.common.bullet.BulletSPI;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.data.entityparts.MovingPart;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.data.entityparts.TimerPart;
import dk.sdu.srm.common.services.IEntityProcessingService;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.srm.common.util.AnimationHandler;


public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    public float fireRate = 1;

    boolean canFire = true;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            int bulletFacingState = positionPart.getFacingState();
            float bulletSpeed = bullet.getBulletSpeed();



            switch (bulletFacingState) {
                case 0:
                    positionPart.setPosition(positionPart.getX() - bulletSpeed, positionPart.getY());
                    break;
                case 1:
                    positionPart.setPosition(positionPart.getX(), positionPart.getY() + bulletSpeed);
                    break;
                case -1:
                    positionPart.setPosition(positionPart.getX(), positionPart.getY() - bulletSpeed);
                    break;
                case 2:
                    positionPart.setPosition(positionPart.getX() + bulletSpeed, positionPart.getY());
                    break;
            }


            TimerPart timerPart = bullet.getPart(TimerPart.class);

            if (timerPart.getExpiration() < 0) {
                world.removeEntity(bullet);
            }
            timerPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);



            }
        }

        @Override
        public Entity createBullet(Entity player, GameData gameData, World world) {

                PositionPart playerPosition = player.getPart(PositionPart.class);
                float playerx = playerPosition.getX();
                float playery = playerPosition.getY();
                int playerFacingState = playerPosition.getFacingState();
                float dt = gameData.getDelta();
                PositionPart bulletPosition = player.getPart(PositionPart.class);

                Entity bullet = new Bullet();
                bullet.add(new PositionPart(playerx, playery, playerFacingState));
                bullet.add(new LifePart(1));
                bullet.add(new TimerPart(1 / 2f));
                bullet.characterAtlas = new TextureAtlas("Bullet/src/main/resources/bullet/bullet.atlas");
                bullet.animationHandler = new AnimationHandler();
                bullet.animationHandler.add("tile015", new Animation<>(bullet.FRAME_TIME, bullet.characterAtlas.findRegions("tile015")));
                bullet.animationHandler.setCurrentAnimation("tile015");
                bullet.setBulletSpeed(1f);
                player.setCanShoot(false);
                return bullet;
        }


}
