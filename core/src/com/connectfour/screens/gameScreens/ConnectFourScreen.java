package com.connectfour.screens.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.EllipseShapeBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.connectfour.Games;
import space.earlygrey.shapedrawer.ShapeDrawer;
import com.badlogic.gdx.Graphics;

import java.awt.*;

public class ConnectFourScreen implements Screen {

    private ExtendViewport vp;
    private final Games game;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    /**Array, kus hoitakse ketaste info. 0 = ketast pole, 1 = kohaliku mängija ketas, 2 = vastase ketas. Kettad pole maatriksis
     * vahemälu kokkuhoidmiseks. Ketta info saamseks kasuta: {@link ConnectFourScreen#getKetas(int, int)}.*/
    public byte[] ringid;
    float aspectRatio;
    OrthographicCamera cam;

    public ConnectFourScreen(final Games game){
        this.game = game;
    }

    @Override
    public void show() {
        aspectRatio = (float) Gdx.graphics.getHeight()/ (float) Gdx.graphics.getWidth();
        cam = new OrthographicCamera();
        vp = new ExtendViewport(game.boardSizeX * 2, game.boardSizeY * 2 + 2, cam);
        vp.apply(true);
        //this.stage = new Stage(game.viewport, game.batch);
        //this.game.viewport.update(1918, 1009);
        this.ringid = new byte[game.boardSizeX * game.boardSizeY];
        Pixmap whitesq = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        whitesq.setColor(1,1,1,1);
        whitesq.fillRectangle(0,0,1,1);
        shapeRenderer = new ShapeRenderer();
        //shapeRenderer.setProjectionMatrix(game.viewport.getCamera().combined);
    }

    @Override
    public void render(float delta) {

        Gdx.gl30.glClearColor(game.backGroundColor.r,game.backGroundColor.g,game.backGroundColor.b,game.backGroundColor.a);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);

        cam.update();
        shapeRenderer.setProjectionMatrix(cam.combined);
        drawBoard(0.2f, 1);

        /*game.batch.begin();
        game.batch.draw((Texture) game.assetsLoader.manager.get(game.assetsLoader.whiteCircle), 0, 0);
        game.batch.draw((Texture) game.assetsLoader.manager.get(game.assetsLoader.whiteCircle), 532, 0);
        game.batch.end();*/

    }

    @Override
    public void resize(int width, int height) {
        vp.update(width,height, true);
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

    private void drawBoard(float spaceBetween, float radius) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (float y = spaceBetween + radius; y <= game.boardSizeY * (2*radius+spaceBetween); y+=(2*radius+spaceBetween)) {
            for (float x = spaceBetween + radius; x <= game.boardSizeX * (2*radius+spaceBetween); x+=(2*radius+spaceBetween)) {
                shapeRenderer.setColor(1, 1, 1, 1);
                shapeRenderer.circle(x, y, radius, 100);
            }
        }
        shapeRenderer.end();
    }

    /**Tagastab ketta antud x ja y koordinaatidel. kettaX ja kettaY on mängulaua gridi, mitte pikslite koordinaadid!*/
    public byte getKetas(int kettaX, int kettaY) {
        return ringid[game.boardSizeX * (kettaY - 1) + kettaX];
    }
    /**Lisab või muudab kettast {@link ConnectFourScreen#ringid} nimekirajs. kettaX ja kettaY on ketta asukoht mängulaua gridis. State määrab ketta kuuluvuse: 0 = ketast pole, 1 = ketas kuulub mängijale, 2 = kettas kuulub vastasele.*/
    public void setKetas(int kettaX, int kettaY, byte state) {
        this.ringid[game.boardSizeX * (kettaY - 1) + kettaX] = state;
    }

    private float[] circleVertices(int radius, int edges) {
        return new float[1];
    }
}
