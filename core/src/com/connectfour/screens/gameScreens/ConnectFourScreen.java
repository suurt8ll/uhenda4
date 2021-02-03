package com.connectfour.screens.gameScreens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.connectfour.Games;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class ConnectFourScreen implements Screen {

    private final Games game;
    private Stage stage;
    private ShapeDrawer shapeDrawer;

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
        this.shapeDrawer = new ShapeDrawer(game.batch, new TextureRegion(new Texture(whitesq)));
    }

    @Override
    public void render(float delta) {
        this.stage.act();
        this.stage.draw();
        game.batch.begin();
        shapeDrawer.line(0,0, 100, 100, Color.RED, 2);
        shapeDrawer.filledEllipse(100, 100, 200, 200, 0, Color.RED, Color.BLACK);
        game.batch.end();
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
