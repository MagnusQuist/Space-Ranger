package dk.sdu.srm.common.data.entityparts;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsManager{
    public static final AssetManager manager = new AssetManager();
    public static final AssetDescriptor<TextureAtlas> PLAYER = new AssetDescriptor<>("assets/player/animations/player.atlas", TextureAtlas.class);
    public static final AssetDescriptor<Skin> UI = new AssetDescriptor<>("assets/skin/ui_skin.json", Skin.class);
    public static final AssetDescriptor<TextureAtlas> HUD = new AssetDescriptor<>("assets/skin/hud_skin.atlas", TextureAtlas.class);
    public static final AssetDescriptor<Texture> BACKGROUND = new AssetDescriptor<>("assets/menu/bg.png", Texture.class);
    public static final AssetDescriptor<Texture> LOGO = new AssetDescriptor<>("assets/menu/logo.png", Texture.class);


    public static void loadTexture(){
        manager.load(PLAYER);
        manager.load(UI);
        manager.load(HUD);
        manager.load(BACKGROUND);
        manager.load(LOGO);
    }

    public static void dispose(){
        manager.dispose();
    }
}
