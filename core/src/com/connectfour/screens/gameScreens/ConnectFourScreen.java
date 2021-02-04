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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
        Pixmap whitesq = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        whitesq.setColor(1,1,1,1);
        whitesq.fillRectangle(0,0,1,1);
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void render(float delta) {

        Gdx.gl30.glClearColor(game.backGroundColor.r,game.backGroundColor.g,game.backGroundColor.b,game.backGroundColor.a);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw((Texture) game.assetsLoader.manager.get(game.assetsLoader.blackCircle), 0, 0);
        game.batch.draw((Texture) game.assetsLoader.manager.get(game.assetsLoader.whiteCircle), 200, 200);
        game.batch.end();

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

    private void drawBoard(int spaceBetween, int radius) {
        for (int y = spaceBetween + radius; y <= game.boardSizeY * (2*radius+spaceBetween); y+=(2*radius+spaceBetween)) {
            for (int x = spaceBetween + radius; x <= game.boardSizeX * (2*radius+spaceBetween); x+=(2*radius+spaceBetween)) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(1, 1, 1, 1);
                shapeRenderer.circle(x, y, radius);
                shapeRenderer.end();
            }
        }
    }
}
