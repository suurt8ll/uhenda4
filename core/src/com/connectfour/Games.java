package com.connectfour;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    public Preferences prefs;

    public GameMenuScreen GAMEMENU;
    public SettingsScreen SETTINGS;

    public InputMultiplexer inputMultiplexer;

    public Object getPreferences() {
        return this.prefs;
    }

    public void changeScreen(Screen s) {
        this.setScreen(s);
    }

    @Override
    public void create() {
        this.assetsLoader = new AssetsLoader();
        assetsLoader.load();
        this.prefs = Gdx.app.getPreferences("MyPrefs");
        this.viewport = new FitViewport(this.ScreenWidth,this.ScreenHeight);
        this.batch = new SpriteBatch();
        this.inputMultiplexer = new InputMultiplexer();
        this.GAMEMENU = new GameMenuScreen(this);
        this.SETTINGS = new SettingsScreen(this);
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
