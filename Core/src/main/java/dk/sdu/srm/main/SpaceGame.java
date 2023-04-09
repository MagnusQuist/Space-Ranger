package dk.sdu.srm.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.sdu.srm.common.data.GameData;
import dk.sdu.srm.common.data.World;
import dk.sdu.srm.common.services.IEntityProcessingService;
import dk.sdu.srm.common.services.IGamePluginService;
import dk.sdu.srm.common.services.IPostEntityProcessingService;
import dk.sdu.srm.common.util.SPILocator;
import dk.sdu.srm.main.Screens.PlayScreen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class SpaceGame extends Game  {

    public SpriteBatch batch; //Den er public fordi at alle vores screens skal kunne have adgang til vores Sprites

    private final GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private List<IPostEntityProcessingService> postEntityProcessors = new ArrayList<>();
    private World world = new World();
    @Override
    public void create() {
        batch = new SpriteBatch();
        System.out.println("SpaceGame");

        System.out.println(getPluginServices());

        for (IGamePluginService iGamePlugin : getPluginServices()) {
            System.out.println("køre denne her?");

            iGamePlugin.start(gameData, world);
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose(){
        batch.dispose();
    }

    private void update(){
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
