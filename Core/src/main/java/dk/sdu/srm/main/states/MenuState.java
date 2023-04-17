package dk.sdu.srm.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dk.sdu.srm.managers.GameStateManager;

public class MenuState extends State {
    private Skin skin;
    private Stage stage;
    private Table table;
    private TextButton playBtn;
    private TextButton settingsBtn;
    private TextButton quitBtn;
    private Texture background;
    private Texture logo;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        skin = new Skin(Gdx.files.internal("assets/skin/ui_skin.json"));
        stage = new Stage(new ScreenViewport());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        playBtn = new TextButton("Play", skin);
        settingsBtn = new TextButton("Settings", skin);
        quitBtn = new TextButton("Quit", skin);

        table.padTop(200);
        table.add(playBtn).padBottom(16);
        table.row();
        table.add(settingsBtn).padBottom(16);
        table.row();
        table.add(quitBtn);

        stage.addActor(table);

        background = new Texture(Gdx.files.internal("assets/menu/bg.png"));
        logo = new Texture(Gdx.files.internal("assets/menu/logo.png"));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {
        if (playBtn.isPressed()) {
            gsm.set(new PlayState(gsm));
        }

        if (quitBtn.isPressed()) {
            Gdx.app.exit();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(logo, Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2, Gdx.graphics.getHeight() - logo.getHeight() - 100);
        sb.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
