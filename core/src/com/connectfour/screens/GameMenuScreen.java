package com.connectfour.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.Games;

public class GameMenuScreen implements Screen {

    private Games game;
    private Viewport viewport;
    private Camera cam;
    private Stage stage;

    private float worldWidth, worldHeight;

    public GameMenuScreen(Games game){
        this.game = game;
        /*this.cam = new OrthographicCamera();
        this.viewport = new ExtendViewport(Games.MONITORWIDTH, Games.MONITORHEIGHT, cam);
        cam.position.set(Games.MONITORWIDTH / 2f, Games.MONITORHEIGHT / 2f, 0);*/
    }

    /**Proov teha hea framework lihtsate ekraadide loomiseks.
     * edgePadding on tühja ala protsent külgedel
     * rowSpacing on objektide vahele jääv ala antud protsendina ekraani kõrgusest*/
    private Stage initStage(Actor[] actorArr, float rowSpacing) {

        if (rowSpacing > 1f / actorArr.length) System.err.println("HOIATUS! Ridade vahe ei saa olla nii suur, sest matemaatika ei luba!");

        int biggestX = 0;
        int heightSum = 0;

        for (Actor actor : actorArr) {
            int x = (int) actor.getWidth();
            int y = (int) actor.getHeight();
            if (x > biggestX) biggestX = x;
            heightSum += y;
        }

        float aspectRatio = Games.MONITORWIDTH / (float) Games.MONITORHEIGHT;
        float idealHeight = biggestX / aspectRatio;
        float realHeight = heightSum / (1 - rowSpacing * actorArr.length + rowSpacing);
        float realWidth = biggestX;
        boolean edgePadding = false;

        //Tõene juhul kui objektid ei mahuks antud laiuse juures vertikaalselt ekraanile.
        if (realHeight > idealHeight) {
            realWidth = aspectRatio * realHeight;
            edgePadding = true;
        }

        worldWidth = realWidth;
        if (edgePadding) {
            worldHeight = realHeight;
        } else {
            worldHeight = idealHeight;
        }

        Camera cam = new OrthographicCamera();
        cam.position.set(worldWidth / 2, worldHeight / 2, 0);
        Viewport viewport = new ExtendViewport(worldWidth, worldHeight, cam);

        Stage stage = new Stage(viewport, game.batch);
        stage.getBatch().setProjectionMatrix(stage.getViewport().getCamera().combined);

        int i = 1;
        for (Actor actor : actorArr) {
            float x = (realWidth - actor.getWidth()) / 2;
            float y;
            if (edgePadding) {
                y = realHeight - i * actor.getHeight() - (i - 1) * realHeight * rowSpacing;
            } else {
                y = idealHeight - ((idealHeight - realHeight) / 2) - i * actor.getHeight() - (i - 1) * idealHeight * rowSpacing;
            }
            actor.setPosition(x, y);
            stage.addActor(actor);
            i += 1;
        }

        game.inputMultiplexer.addProcessor(stage);

        return stage;

    }

    @Override
    public void show() {
        ImageButton host = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.host))));
        host.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.player2.setAI(false);
                game.changeScreen(game.CONNECTFOUR);
                return true;
            }
        });

        ImageButton join = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.join))));
        join.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.player2.setAI(false);
                game.changeScreen(game.CONNECTFOUR);
                return true;
            }
        });

        ImageButton pvaibutton = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.vsai))));
        pvaibutton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.player2.setAI(true);
                //TODO Implement minmax.
                game.changeScreen(game.CONNECTFOUR);
                return true;
            }
        });

        stage = initStage(new Actor[]{host, join, pvaibutton}, 0.1f);
        game.inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl30.glClearColor(0, 0, 0, 1);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        stage.getCamera().position.set(worldWidth / 2f, worldHeight / 2f, 0);
        game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
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
