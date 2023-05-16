package dk.sdu.srm.main.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.data.entityparts.LifePart;
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.main.SpaceGame;

public class Hud implements Disposable {
    public Stage stage;
    private Skin skin;
    private Entity player = null;
    private TextureAtlas hudAtlas;
    private Viewport viewport;
    private static Label healthCount;
    private static Label healthText;

    public Hud (GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            this.player = player;
        }

        skin = new Skin(Gdx.files.internal("Core/src/main/resources/skin/ui_skin.json"));
        hudAtlas = new TextureAtlas("Core/src/main/resources/skin/hud_skin.atlas");
        viewport = new FitViewport(SpaceGame.WIDTH, SpaceGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);

        Table table = new Table();
        table.setWidth(stage.getWidth());

        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        table.padTop(20);

        if (player != null) {
            LifePart lifePart = player.getPart(LifePart.class);
            healthText = new Label("Health: ", skin, "ui_text");
            //healthCount = new Label(Integer.toString(player.getHealth()), skin, "ui_text");

            table.add(healthText).padRight(16);
            for (int i = 0; i < lifePart.getLife(); i++) {
                table.add(new Image(hudAtlas.createSprite("heart"))).size(18, 18).padRight(6);
            }
            //table.add(healthCount).padRight(20);
        }

        stage.addActor(table);
    }

    public void update(float dt) {
        stage.act(dt);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
