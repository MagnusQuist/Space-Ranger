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
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.main.SpaceGame;

public class Hud implements Disposable {
    public Stage stage;
    private Skin skin;
    private Entity player = null;
    private TextureAtlas hudAtlas;
    private Viewport viewport;
    private static Label healthCount;
    private static Label coinCount;
    private static Label armorCount;
    private Image heart;
    private Image coin;
    private Image armor;

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
            healthCount = new Label(Integer.toString(player.getHealth()), skin, "ui_text");
            heart = new Image(hudAtlas.createSprite("heart"));

            coinCount = new Label(Integer.toString(player.getCoins()), skin, "ui_text");
            coin = new Image(hudAtlas.createSprite("coin"));

            armorCount = new Label(Integer.toString(player.getArmor()), skin, "ui_text");
            armor = new Image(hudAtlas.createSprite("armor"));

            table.row();
            table.add(heart).size(18, 18).padRight(6);
            table.add(healthCount).padRight(20);

            table.add(coin).size(14, 18).padRight(6);
            table.add(coinCount).padRight(20);

            table.add(armor).size(18, 18).padRight(6);;
            table.add(armorCount);
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
