package dk.sdu.srm.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    private Image bg;
    private Image title;
    private Music ambiantMusic;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        skin = new Skin(Gdx.files.internal("Core/src/main/resources/skin/ui_skin.json"));

        ambiantMusic = Gdx.audio.newMusic(Gdx.files.internal("Core/src/main/resources/music/main_menu.ogg"));
        ambiantMusic.play();
        ambiantMusic.setLooping(true);
        ambiantMusic.setVolume(0.2f);

        bg = new Image(new Texture("Core/src/main/resources/menu/bg.png"));
        title = new Image(new Texture("Core/src/main/resources/menu/logo.png"));

        stage = new Stage(new ScreenViewport());

        Group background = new Group();
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Group foreground = new Group();
        foreground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());

        playBtn = new TextButton("Play", skin);
        settingsBtn = new TextButton("Settings", skin);
        quitBtn = new TextButton("Quit", skin);

        title.setPosition(Gdx.graphics.getWidth() / 2 - title.getWidth() / 2, Gdx.graphics.getHeight() - title.getHeight() - 100);

        table.padTop(200);
        table.add(playBtn).padBottom(16);
        table.row();
        table.add(settingsBtn).padBottom(16);
        table.row();
        table.add(quitBtn);

        stage.addActor(background);
        stage.addActor(foreground);

        background.addActor(bg);
        foreground.addActor(title);
        foreground.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {
        if (playBtn.isPressed()) {
            stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                @Override
                public void run() {
                    gsm.set(new IntroductionStage(gsm));
                }
            })));

            ambiantMusic.stop();
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
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        ambiantMusic.dispose();
        System.out.println("test");
    }
}
