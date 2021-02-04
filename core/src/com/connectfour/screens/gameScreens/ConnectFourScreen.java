package com.connectfour.screens.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.connectfour.Games;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ConnectFourScreen implements Screen {

    private final Games game;
    private Stage stage;
    private ShapeRenderer shapeRenderer;

    public ConnectFourScreen(final Games game){
        this.game = game;
    }

    @Override
    public void show() {
        this.stage = new Stage(game.viewport, game.batch);
        System.out.println("negger");
        Pixmap whitesq = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        whitesq.setColor(1,1,1,1);
        whitesq.fillRectangle(0,0,1,1);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl30.glClearColor(game.backGroundColor.r,game.backGroundColor.g,game.backGroundColor.b,game.backGroundColor.a);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.stage.draw();
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
        stage.dispose();
    }
}
