package com.connectfour;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.screens.GameMenuScreen;
import com.connectfour.screens.MainMenuScreen;
import com.connectfour.screens.SettingsScreen;
import com.connectfour.screens.gameScreens.ConnectFourScreen;

public class Games extends Game {

    public Viewport viewport;
    public SpriteBatch batch;
    public Player player1;
    public Skin skin;
    public Player player2;
    public int ScreenWidth = 800;
    public int ScreenHeight = 600;
    public AssetsLoader assetsLoader;
    public Prefs prefs;
    public int boardSizeX;
    public int boardSizeY;

    public GameMenuScreen GAMEMENU;
    public SettingsScreen SETTINGS;
    public MainMenuScreen MAINMENU;
    public ConnectFourScreen CONNECTFOUR;

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
        this.skin = assetsLoader.manager.get(assetsLoader.uiSkinJson,Skin.class);
        this.player1 = new Player(this.prefs.getPlayer1Name(),0,new Color(1,1,0,1));
        this.player2 = new Player(this.prefs.getPlayer2Name(),1, new Color(1,0,1,1));
        this.inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(this.inputMultiplexer);
        this.GAMEMENU = new GameMenuScreen(this);
        this.SETTINGS = new SettingsScreen(this);
        this.MAINMENU = new MainMenuScreen(this);
        this.CONNECTFOUR = new ConnectFourScreen(this);
        this.setScreen(this.MAINMENU);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.assetsLoader.manager.dispose();
        super.dispose();
    }


    public Prefs getPreferences() {
        return prefs;
    }

    public void setBoardsizex(int boardx) {
        this.boardSizeX = boardx;
    }

    public void setBoardsizey(int boardy) {
        this.boardSizeY = boardy;
    }
}
