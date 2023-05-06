package dk.sdu.srm.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameMap;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.main.overlays.Hud;
import dk.sdu.srm.managers.GameStateManager;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayState extends State {
    private Stage stage;
    private Image bg;
    private float elapsedTime = 0;
    private Hud hud;
    private final GameMap map;
    private World world;
    private Box2DDebugRenderer b2dr;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        stage = new Stage(new ScreenViewport());
        bg = new Image(new Texture("Core/src/main/resources/game_bg.png"));

        cam.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();

        Group background = new Group();
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(background);
        background.addActor(bg);

        map = gsm.world.getGameMap();
        hud = new Hud(gsm.gameData, gsm.world);
    }
    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        hud.update(dt);

        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gsm.gameData, gsm.world);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        elapsedTime += Gdx.graphics.getDeltaTime();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        map.render(cam);

        for (Entity e : gsm.world.getEntities()) {
            PositionPart pos = e.getPart(PositionPart.class);
            sb.begin();
            TextureRegion frame = e.animationHandler.getFrame();
            if (pos.getFacingState() == 0 && !frame.isFlipX()) { frame.flip(true, false); }
            if (pos.getFacingState() == 2 && frame.isFlipX()) { frame.flip(true, false); }
            sb.draw(frame, pos.getX(), pos.getY(), frame.getRegionWidth(), frame.getRegionHeight());
            sb.end();

            if (e instanceof Player) {
                cam.position.set(pos.getX(), pos.getY(), 0);
                cam.update();
            }
        }

        sb.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        hud.dispose();
        world.dispose();
        b2dr.dispose();
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    // Collection of Map SPI maybe :)
}
