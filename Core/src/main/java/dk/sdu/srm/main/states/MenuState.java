package dk.sdu.srm.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.srm.main.SpaceGame;
import dk.sdu.srm.managers.GameStateManager;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private BitmapFont font;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture(Gdx.files.internal("assets/menu/menu_bg.png"));
        playBtn = new Texture(Gdx.files.internal("assets/menu/play_btn.png"));
        font = new BitmapFont();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, SpaceGame.WIDTH, SpaceGame.HEIGHT);
        // position play button center of screen
        sb.draw(playBtn, (SpaceGame.WIDTH / 2) - (playBtn.getWidth() / 2), SpaceGame.HEIGHT / 2);
        // Draw title text in center of screen 60px from the top
        font.draw(sb, "Space Game", (SpaceGame.WIDTH / 2) - 50, SpaceGame.HEIGHT - 60);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
