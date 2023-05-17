package dk.sdu.srm.main.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.srm.common.data.Entity;
import dk.sdu.srm.common.data.GameMap;
import dk.sdu.srm.common.data.entityparts.PositionPart;
import dk.sdu.srm.common.enemy.Enemy;
import dk.sdu.srm.common.player.Player;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IPostEntityProcessingService;
import dk.sdu.srm.main.overlays.Hud;
import dk.sdu.srm.managers.GameStateManager;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class PlayState extends State {
    private float elapsedTime = 0;
    private Hud hud;
    private final GameMap map;
    private ShapeRenderer sr;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        cam.setToOrtho(false, 25, 15);
        cam.update();

        sr = new ShapeRenderer();
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

        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gsm.gameData, gsm.world);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        map.render(cam);
        map.debug();

        for (Entity e : gsm.world.getEntities()) {
            PositionPart pos = e.getPart(PositionPart.class);
            TextureRegion frame = e.animationHandler.getFrame();

            /** DEBUG ONLY */
            // Draw player tile
            if (e instanceof Player) {
                sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(Color.GOLD);
                GameMap gameMap = gsm.world.getGameMap();
                TiledMapTileLayer floorLayer = gameMap.getFloorLayer();

                // Convert player position in 800x450 to tile position in 25x15 at center of player
                int tileX = (int) ((pos.getX() + (e.getCollision().width / 2)) / (800 / 25));
                int tileY = (int) ((pos.getY() + (e.getCollision().height / 2)) / (450 / 15));

                // Get the current tile
                TiledMapTileLayer.Cell cell = floorLayer.getCell(tileX, tileY);

                // Draw the tile
                sr.rect(cell.getTile().getOffsetX() + tileX * 32, cell.getTile().getOffsetY() + tileY * 32, 32, 32);

                sr.end();
            }

            for (Entity entity : gsm.world.getEntities()){
                PositionPart entityPos = entity.getPart(PositionPart.class);
                if (entity instanceof Player){
                    sr.begin(ShapeRenderer.ShapeType.Line);
                    sr.setColor(Color.BLUE);
                    sr.rect(entityPos.getX(), entityPos.getY(), 13 * entity.SPRITE_SIZE, 21 * entity.SPRITE_SIZE);
                    sr.end();
                }
                if (entity instanceof Enemy){
                    sr.begin(ShapeRenderer.ShapeType.Line);
                    sr.setColor(Color.YELLOW);
                    sr.rect(entityPos.getX(), entityPos.getY(), 16 * entity.SPRITE_SIZE, 12 * entity.SPRITE_SIZE);
                    sr.end();
                }
            }



            sb.begin();
            if (pos.getFacingState() == 0 && !frame.isFlipX()) { frame.flip(true, false); }
            if (pos.getFacingState() == 2 && frame.isFlipX()) { frame.flip(true, false); }
            sb.draw(frame, pos.getX(), pos.getY(), frame.getRegionWidth() * e.SPRITE_SIZE, frame.getRegionHeight() * e.SPRITE_SIZE);
            sb.end();
        }

        hud.stage.draw();
    }

    @Override
    public void dispose() {
        hud.dispose();
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    // Collection of Map SPI maybe :)
}
