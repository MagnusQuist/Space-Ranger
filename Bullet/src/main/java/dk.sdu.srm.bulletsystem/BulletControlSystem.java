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
        for (Entity bullet : world.getEntities(Bullet.class)){
            PositionPart positionPart = bullet.getPart(PositionPart.class);
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
        float dt = gameData.getDelta();
        float speed = 1;

        Entity bullet = new Bullet();
        bullet.setRadius(2);


        bullet.add(new PositionPart(playerx,playery));
        bullet.add(new LifePart(1));
        bullet.add(new TimerPart(3));

        texture = new Texture(Gdx.files.internal("Bullet/src/main/resources/bullet.png"));

        bullet.setTexture(texture);
        bullet.animationHandler.setCurrentAnimation("idle");



        System.out.println("pewpew");
        return bullet;
    }
}
