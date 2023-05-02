package dk.sdu.srm.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dk.sdu.srm.managers.GameStateManager;

public class IntroductionStage  extends State {
    private Stage stage;
    private Skin skin;
    private Table table;
    private Label skip;
    private Label title;
    private Label text;
    private Image bg;

    protected IntroductionStage(GameStateManager gsm) {
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

        title = new Label("Stranded in Space", skin);

        text = new Label("After crash-landing on a hostile planet, " +
                "you find yourself alone and vulnerable. But with cunning, " +
                "courage, and a little bit of luck, you just might make it off " +
                "this planet alive. Explore randomly generated environments " +
                "filled with deadly creatures and treacherous terrain, scavenge " +
                "for resources, and upgrade your skills as you go. Will you be able " +
                "to outwit your enemies, master the art of survival, and find your " +
                "way back home? The fate of your mission rests in your hands. " +
                "Let the adventure begin!", skin, "ui_text");
        text.setWidth(100);
        text.setWrap(true);
        text.setFontScale(1f);
        text.setAlignment(Align.center);


        skip = new Label("Press SPACE to continue", skin, "ui_text");
        skip.setFontScale(.8f);

        table.add(title);
        table.row();
        table.add(text).padTop(50).width(500);
        table.row();
        table.add(skip).padTop(100);

        stage.addActor(background);
        stage.addActor(foreground);

        background.addActor(bg);
        foreground.addActor(table);

        stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.0f)));

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
            stage.addAction(Actions.sequence(Actions.fadeOut(1.0f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    gsm.set(new PlayState(gsm));
                }
            })));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

        if (skip.getColor().a < 1) {
            skip.addAction(Actions.fadeIn(1.0f));
        } else {
            skip.addAction(Actions.fadeOut(1.0f));
        }
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
