package com.connectfour;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.screens.GameMenuScreen;
import com.connectfour.screens.MainMenuScreen;
import com.connectfour.screens.SettingsScreen;
import com.connectfour.screens.gameScreens.ConnectFourScreen;

import java.awt.*;

public class Games extends Game {

    public static int MONITORWIDTH;
    public static int MONITORHEIGHT;

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
    //FIXME muutuja spacing Prefs klassi implementida
    public float spacing;

    public GameMenuScreen GAMEMENU;
    public SettingsScreen SETTINGS;
    public MainMenuScreen MAINMENU;
    public ConnectFourScreen CONNECTFOUR;

    public Music music;

    public InputMultiplexer inputMultiplexer;
    public Color backGroundColor;

    @Override
    public void create() {
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        MONITORWIDTH = dimension.width;
        MONITORHEIGHT = dimension.height;

        this.assetsLoader = new AssetsLoader();
        this.assetsLoader.init();
        this.assetsLoader.load();
        this.prefs = new Prefs();
        this.prefs.init();

        this.boardSizeX = this.prefs.getBoardx();
        this.boardSizeY = this.prefs.getBoardy();
        this.spacing = 0.2f;

        this.viewport = new FitViewport(this.ScreenWidth,this.ScreenHeight, new OrthographicCamera(this.ScreenWidth,this.ScreenHeight));
        this.batch = new SpriteBatch();
        this.skin = assetsLoader.manager.get(assetsLoader.uiSkinJson,Skin.class);

        this.player1 = new Player(this.prefs.getPlayer1Name(), (byte) 1,new Color(HexToColor(this.prefs.getPlayer1Color())));
        this.player2 = new Player(this.prefs.getPlayer2Name(), (byte) 2, new Color(HexToColor(this.prefs.getPlayer2Color())));
        this.backGroundColor = HexToColor(this.prefs.getBackgroundColor());

        this.music = assetsLoader.manager.get(assetsLoader.musicfile, Music.class);
        this.music.setVolume(this.prefs.getMusicVolume());
        this.music.play();

        this.inputMultiplexer = new InputMultiplexer();
        this.inputMultiplexer.addProcessor(new InputAdapter(){
            @Override
            public boolean keyDown (int keycode) {
                if (keycode == Input.Keys.ESCAPE){
                    changeScreen(MAINMENU);
                    return true;
                }
                return false;
            }

        });
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
        this.music.dispose();
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

    public static Color HexToColor(String hex) {
        hex = hex.replace("#", "");
        switch (hex.length()) {
            case 6:
                return new Color(
                        Integer.valueOf(hex.substring(0, 2), 16)/255f,
                        Integer.valueOf(hex.substring(2, 4), 16)/255f,
                        Integer.valueOf(hex.substring(4, 6), 16)/255f,1);
            case 8:
                return new Color(
                        Integer.valueOf(hex.substring(0, 2), 16)/255f,
                        Integer.valueOf(hex.substring(2, 4), 16)/255f,
                        Integer.valueOf(hex.substring(4, 6), 16)/255f,
                        Integer.valueOf(hex.substring(6, 8), 16)/255f);
        }
        return null;
    }

    public void changeScreen(Screen s) {
        this.setScreen(s);
    }
}
