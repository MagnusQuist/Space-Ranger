package dk.sdu.srm.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.EnemyType;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.main.SpaceGame;
import dk.sdu.srm.managers.GameStateManager;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayState extends State {
    private float elapsedTime;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }
    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gsm.gameData, gsm.world);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        elapsedTime += Gdx.graphics.getDeltaTime();
        for (Entity e : gsm.world.getEntities()) {
            PositionPart pos = e.getPart(PositionPart.class);
            sb.begin();
            TextureRegion frame = e.animationHandler.getFrame();
            if (pos.getFacingState() == 0 && !frame.isFlipX()) { frame.flip(true, false); }
            if (pos.getFacingState() == 1 && frame.isFlipX()) { frame.flip(true, false); }
            sb.draw(frame, pos.getX(), pos.getY(), frame.getRegionWidth(), frame.getRegionHeight());
            sb.end();
        }
    }

    @Override
    public void dispose() {

    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
