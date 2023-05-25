package dk.sdu.srm.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.managers.GameStateManager;

public class EndStage  extends State {
    private Stage stage;
    private Skin skin;
    private TextButton replayBtn;
    private TextButton quitBtn;
    private Table table;
    private Label skip;
    private Label title;
    private Label text;
    private Image bg;

    protected EndStage(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("Core/src/main/resources/skin/ui_skin.json"));

        bg = new Image(new Texture("Core/src/main/resources/introduction/introduction_bg.png"));

        Group background = new Group();
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Group foreground = new Group();
        foreground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight() - 100);

        replayBtn = new TextButton("Replay game *NOT WORKING*", skin);
        quitBtn = new TextButton("Quit", skin);


        title = new Label("You've DIED", skin);
        title.setFontScale(3);


        table.add(title).padBottom(16);
        table.row();
        table.add(replayBtn).padBottom(16);
        table.row();
        table.add(quitBtn);

        stage.addActor(background);
        stage.addActor(foreground);

        background.addActor(bg);
        foreground.addActor(table);

        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.0f)));

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
        if (replayBtn.isPressed()) {
            stage.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    gsm.set(new PlayState(gsm));
                }
            })));
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
    }
}
