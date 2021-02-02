package com.connectfour;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.screens.GameMenuScreen;
import com.connectfour.screens.MainMenuScreen;
import com.connectfour.screens.SettingsScreen;
import com.connectfour.screens.gameScreens.GamesEnum;

public class Games extends Game {
    public Viewport viewport;
    public Batch batch;
    public Player player1;
    public Skin skin;
    public Player player2;
    public GamesEnum selectedGameType;
    public int ScreenWidth = 800;
    public int ScreenHeight = 600;
    public AssetsLoader assetsLoader = new AssetsLoader();
    public Preferences prefs;

    public GameMenuScreen GAMEMENU;
    public SettingsScreen SETTINGS;

    public InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public Object getPreferences() {
        return this.prefs;
    }

    public void changeScreen(Screen s) {
        this.setScreen(s);
    }

    @Override
    public void create() {
        assetsLoader.load();
        this.prefs = Gdx.app.getPreferences("MyPrefs");
        this.GAMEMENU = new GameMenuScreen(this);
        this.SETTINGS = new SettingsScreen(this);
        this.viewport = new FitViewport(this.ScreenWidth,this.ScreenHeight);
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
