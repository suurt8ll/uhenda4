package com.connectfour.screens.gameScreens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.connectfour.Games;

public class ConnectFourScreen implements Screen {
    private Games game;
    private Stage stage;

    public ConnectFourScreen(final Games game){
        this.game = game;
        stage = new Stage(game.viewport,game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
