package com.connectfour.screens.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
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
import com.badlogic.gdx.Graphics;

import java.awt.*;

public class ConnectFourScreen implements Screen {

    private final Games game;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    /**Array, kus hoitakse ketaste info. 0 = ketast pole, 1 = kohaliku mängija ketas, 2 = vastase ketas. Kettad pole maatriksis
     * vahemälu kokkuhoidmiseks. Ketta info saamseks kasuta: {@link ConnectFourScreen#getKetas(int, int)}.*/
    public byte[] ringid;

    public ConnectFourScreen(final Games game){
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.graphics.setWindowedMode(1918, 1009);
        this.ringid = new byte[game.boardSizeX * game.boardSizeY];
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
        game.batch.draw((Texture) game.assetsLoader.manager.get(game.assetsLoader.whiteCircle), 0, 0);
        game.batch.draw((Texture) game.assetsLoader.manager.get(game.assetsLoader.whiteCircle), 532, 0);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width,height,true);
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
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

    /**Tagastab ketta antud x ja y koordinaatidel. kettaX ja kettaY on mängulaua gridi, mitte pikslite koordinaadid!*/
    public byte getKetas(int kettaX, int kettaY) {
        return ringid[game.boardSizeX * (kettaY - 1) + kettaX];
    }
    /**Lisab või muudab kettast {@link ConnectFourScreen#ringid} nimekirajs. kettaX ja kettaY on ketta asukoht mängulaua gridis. State määrab ketta kuuluvuse: 0 = ketast pole, 1 = ketas kuulub mängijale, 2 = kettas kuulub vastasele.*/
    public void setKetas(int kettaX, int kettaY, byte state) {
        this.ringid[game.boardSizeX * (kettaY - 1) + kettaX] = state;
    }
}
