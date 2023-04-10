package dk.sdu.srm.playersystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.services.IEntityProcessingService;

public class PlayerControlSystem extends InputAdapter implements IEntityProcessingService {

    Texture playerModel;
    float speed = 50.0f;
    float playerx = 0;
    float playery = 8;

    public void showModel(Entity entity) {
        playerModel = new Texture(Gdx.files.internal("assets/badlogic.jpg"));
        System.out.println("Jeg er PlayerControlSystem.show()");
    }

    @Override
    public void process(GameData gameData, World world) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            System.out.println("W");
            playery+= Gdx.graphics.getDeltaTime()*speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            System.out.println("S");
            playery-= Gdx.graphics.getDeltaTime()*speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            System.out.println("D");
            playerx+= Gdx.graphics.getDeltaTime()*speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            System.out.println("A");
            playerx+= Gdx.graphics.getDeltaTime()*speed;
        }
    }
}
