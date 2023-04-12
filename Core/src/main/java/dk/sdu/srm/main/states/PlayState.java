package dk.sdu.srm.main.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.main.SpaceGame;
import dk.sdu.srm.managers.GameStateManager;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayState extends State {

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, SpaceGame.WIDTH / 2, SpaceGame.HEIGHT / 2);
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
        for (Entity e : gsm.world.getEntities()) {
            PositionPart pos = e.getPart(PositionPart.class);
            sb.begin();
            sb.draw(e.getTexture(), pos.getX(), pos.getY());
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