package com.connectfour;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.screens.GameMenuScreen;
import com.connectfour.screens.MainMenuScreen;
import com.connectfour.screens.SettingsScreen;
import com.connectfour.screens.gameScreens.GamesEnum;

public class Games extends Game {
    public Viewport viewport;
    public SpriteBatch batch;
    public Player player1;
    public Skin skin;
    public Player player2;
    public GamesEnum selectedGameType;
    public int ScreenWidth = 800;
    public int ScreenHeight = 600;
    public AssetsLoader assetsLoader;
    public Prefs prefs;

    public GameMenuScreen GAMEMENU;
    public SettingsScreen SETTINGS;

    public InputMultiplexer inputMultiplexer;

    public void changeScreen(Screen s) {
        this.setScreen(s);
    }

    @Override
    public void create() {
        this.assetsLoader = new AssetsLoader();
        this.assetsLoader.init();
        this.assetsLoader.load();
        this.prefs = new Prefs();
        this.viewport = new FitViewport(this.ScreenWidth,this.ScreenHeight);
        this.batch = new SpriteBatch();
        this.skin = new Skin();
        this.skin.load(Gdx.files.internal(assetsLoader.uiSkinJson));//assetsLoader.manager.get(assetsLoader.uiSkinJson,Skin.class);
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(this.inputMultiplexer);
        this.GAMEMENU = new GameMenuScreen(this);
        this.SETTINGS = new SettingsScreen(this);
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        this.GAMEMENU.dispose();
        this.SETTINGS.dispose();
        this.batch.dispose();
        this.assetsLoader.manager.dispose();
        super.dispose();
    }


    public Prefs getPreferences() {
        return prefs;
    }
}
