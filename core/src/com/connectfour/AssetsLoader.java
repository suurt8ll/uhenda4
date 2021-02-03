package com.connectfour;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class AssetsLoader {
    public AssetManager manager = new AssetManager();
    public final String playButtonImg = "core/assets/play.png";
    public final String settingsButtonImg = "core/assets/settings.png";
    public final String robotoBlack = "Roboto-Black.ttf";
    private FreetypeFontLoader.FreeTypeFontLoaderParameter mySmallFont;

    public void load() {
        manager.load(playButtonImg, Texture.class);
        manager.load(settingsButtonImg, Texture.class);
        manager.load(robotoBlack, BitmapFont.class, mySmallFont);
        manager.finishLoading();
    }
    public void init(){
        FileHandleResolver resolver = new InternalFileHandleResolver();
        this.manager.setLoader(FreeTypeFontGenerator .class, new FreeTypeFontGeneratorLoader(resolver));
        this.manager.setLoader(BitmapFont .class, ".ttf", new FreetypeFontLoader(resolver));
        this.mySmallFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        this.mySmallFont.fontFileName = robotoBlack;
        this.mySmallFont.fontParameters.size = 10;
    }

}
