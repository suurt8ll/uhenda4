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
    public final String arrowDownImg = "arrowdown.png";
    public final String playButtonImg = "play.png";
    public final String settingsButtonImg = "settings.png";
    public final String robotoBlack = "fonts/Roboto-Black.ttf";
    public final String uiSkinAtlas = "skin/uiskin.atlas";
    public final String uiSkinJson = "skin/uiskin.json";
    public final String uiSkinFont = "skin/default.fnt";
    public final String uiSkinPng = "skin/uiskin.png";
    public final String musicfile = "music/music.mp3";
    public final String whiteCircle = "whitecircle_512x512.png";
    public final String newGame = "newgame_512x122.png";
    public final String exit = "exit_512x122.png";
    //TODO Faili suuruseid tuleks väiksemaks saada.
    public final String host = "host_1937x256.png";
    public final String join = "join_1834x256.png";
    public final String vsai = "vsai_1733x256.png";
    public final String history = "history.png";
    public final String pvp = "pvp.png";
    private FreetypeFontLoader.FreeTypeFontLoaderParameter mySmallFont;

    public void load() {
        manager.load(arrowDownImg, Texture.class);
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
        manager.load(newGame, Texture.class);
        manager.load(exit, Texture.class);
        manager.load(host, Texture.class);
        manager.load(join, Texture.class);
        manager.load(vsai, Texture.class);
        manager.load(history, Texture.class);
        manager.load(pvp,Texture.class);
        manager.finishLoading();
    }
    public void init(){
        FileHandleResolver resolver = new InternalFileHandleResolver();
        this.manager.setLoader(FreeTypeFontGenerator .class, new FreeTypeFontGeneratorLoader(resolver));
        this.manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        this.mySmallFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        this.mySmallFont.fontFileName = robotoBlack;
        this.mySmallFont.fontParameters.size = 50;
    }



}
