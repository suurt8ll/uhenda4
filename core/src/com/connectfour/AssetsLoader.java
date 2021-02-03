package com.connectfour;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsLoader {
    public AssetManager manager = new AssetManager();
    public final String playButtonImg = "play.png";
    public final String settingsButtonImg = "settings.png";
    public final String robotoBlack = "fonts/Roboto-Black.ttf";
    public final String uiSkinAtlas = "skin/uiskin.atlas";
    public final String uiSkinJson = "skin/uiskin.json";
    public final String uiSkinFont = "skin/default.fnt";
    public final String uiSkinPng = "skin/uiskin.png";
    private Preferences preferences;
    private FreetypeFontLoader.FreeTypeFontLoaderParameter mySmallFont;

    public void load() {
        manager.load(playButtonImg, Texture.class);
        manager.load(settingsButtonImg, Texture.class);
        manager.load(robotoBlack, BitmapFont.class, mySmallFont);
        manager.load(uiSkinAtlas, TextureAtlas.class);
        //manager.load(uiSkinPng,Texture.class);
        manager.load(uiSkinJson, Skin.class, new SkinLoader.SkinParameter(uiSkinAtlas));
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
