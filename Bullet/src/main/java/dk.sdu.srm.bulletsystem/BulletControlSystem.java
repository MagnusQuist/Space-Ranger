package dk.sdu.srm.bulletsystem;

import com.badlogic.gdx.Gdx;
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
import dk.sdu.srm.playersystem.Player;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    Texture texture;



    @Override
    public void process(GameData gameData, World world) {
        float bulletspeed = 1;
        for (Entity bullet : world.getEntities(Bullet.class)){
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            positionPart.setPosition(positionPart.getX()+bulletspeed,positionPart.getY()+bulletspeed);
            TimerPart timerPart = bullet.getPart(TimerPart.class);

            if (timerPart.getExpiration() < 0){
                world.removeEntity(bullet);
            }
            timerPart.process(gameData,bullet);
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

        Entity bullet = new Bullet();
        bullet.setRadius(2);


        bullet.add(new PositionPart(playerx,playery));
        bullet.add(new LifePart(1));
        bullet.add(new TimerPart(3));

        texture = new Texture(Gdx.files.internal("assets/bullet/animations/bullet.png"));

        bullet.setTexture(texture);

        /*
        switch(playerFacingState) {
            case 0:
                bullet.animationHandler.setCurrentAnimation("left");
                break;
            case 1:
                bullet.animationHandler.setCurrentAnimation("right");
                break;
            case 2:
                bullet.animationHandler.setCurrentAnimation("up");
                break;
            case 3:
                bullet.animationHandler.setCurrentAnimation("down");
                break;
            default:
                bullet.animationHandler.setCurrentAnimation("idle");
        }

         */
        bullet.animationHandler.setCurrentAnimation("idle");


        System.out.println("pewpew");
        return bullet;
    }
}
