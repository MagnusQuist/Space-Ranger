package dk.sdu.srm.common.data.entityparts;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManager{
    public static final AssetManager manager = new AssetManager();

    //public static final String player = "assets/player/elf_side01_idle.png";
    public static final AssetDescriptor<Texture> player = new AssetDescriptor<>("assets/player/elf_side01_idle.png", Texture.class);

    public static void loadTexture(){
        manager.load(player);
    }

    public static void dispose(){
        manager.dispose();
    }
}
