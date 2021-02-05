package com.connectfour;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
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
    public final String musicfile = "music/music.mp3";
    public final String whiteCircle = "whitecircle_512x512.png";
    public final String blackCircle = "blackcircle.png";
    private FreetypeFontLoader.FreeTypeFontLoaderParameter mySmallFont;

    public void load() {
        manager.load(playButtonImg, Texture.class);
        manager.load(settingsButtonImg, Texture.class);
        manager.load(robotoBlack, BitmapFont.class, mySmallFont);
        manager.load(uiSkinAtlas, TextureAtlas.class);
        manager.load(uiSkinJson, Skin.class, new SkinLoader.SkinParameter(uiSkinAtlas));
        manager.load(musicfile, Music.class);
        TextureLoader.TextureParameter p = new TextureLoader.TextureParameter();
        p.genMipMaps = true;
        p.minFilter = Texture.TextureFilter.MipMapLinearNearest;
        manager.load(whiteCircle, Texture.class, p);
        manager.load(blackCircle, Texture.class);
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
