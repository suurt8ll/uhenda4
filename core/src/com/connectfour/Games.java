package com.connectfour;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    public Preferences prefs = Gdx.app.getPreferences("savedata");

    public GameMenuScreen GAMEMENU = new GameMenuScreen(this);
    public SettingsScreen SETTINGS = new SettingsScreen(this);

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
        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
