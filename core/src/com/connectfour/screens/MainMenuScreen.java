package com.connectfour.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.connectfour.Games;

public class MainMenuScreen implements Screen {
    private final Games game;
    Stage stage;
    Texture texture;
    public MainMenuScreen(final Games game){
        this.game = game;
        game.ScreenWidth = 800;
        game.ScreenHeight = 600;
        Gdx.graphics.setWindowedMode(game.ScreenWidth, game.ScreenHeight);
    }

    @Override
    public void show() {
        stage = new Stage(game.viewport,game.batch);
        ImageButton playbutton = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture) game.assetsLoader.manager.get(game.assetsLoader.playButtonImg))));
        playbutton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.changeScreen(Games.GAMEMENU);
                return true;
            }
        });

        ImageButton settingsbutton = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture) game.assetsLoader.manager.get(game.assetsLoader.settingsButtonImg))));
        settingsbutton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.changeScreen(Games.SETTINGS);
                return true;
            }
        });
        Table table = new Table();
        table.setFillParent(true);
        table.add(playbutton).width(150).height(100);
        table.row();
        table.add(settingsbutton);
        stage.addActor(table);
        game.inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl30.glClearColor(1,1,0,1);
        Gdx.gl30.glClearColor(1, 1, 0, 1);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        game.inputMultiplexer.removeProcessor(stage);
        stage.dispose();
    }
}
