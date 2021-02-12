package com.connectfour.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public GameMenuScreen(Games game){
        this.game = game;
        this.cam = new OrthographicCamera();
        this.viewport = new ExtendViewport(Games.MONITORWIDTH, Games.MONITORHEIGHT, cam);
        cam.position.set(Games.MONITORWIDTH / 2f, Games.MONITORHEIGHT / 2f, 0);
    }

    /**Proov teha hea framework lihtsate ekraadide loomiseks.
     * edgePadding on tühja ala protsent külgedel
     * rowSpacing on objektide vahele jääv ala antud protsendina ekraani kõrgusest*/
    private ExtendViewport createViewport(ImageButton[] imgButtonArr, float edgePadding, float rowSpacing) {

        int biggestX = 0;
        int heightSum = 0;

        for (ImageButton button : imgButtonArr) {
            int x = (int) button.getWidth();
            int y = (int) button.getHeight();
            if (x > biggestX) biggestX = x;
            heightSum += y;
        }

        return null;

    }

    @Override
    public void show() {
        stage = new Stage(viewport, game.batch);
        final int edgesPadding = 100;
        float scale = 1;
        float maxTextHeight = Games.MONITORHEIGHT / 5f;

        ImageButton host = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.host))));

        float textHeight = host.getHeight();
        float textWidth = host.getWidth();

        if (maxTextHeight < textHeight) {
            scale = maxTextHeight / textHeight;
            if (Games.MONITORWIDTH < textWidth * scale) {
                scale = Games.MONITORWIDTH / host.getWidth();
            }
        }
        textWidth = scale * host.getWidth();
        textHeight *= scale;

        host.setBounds(0, Games.MONITORHEIGHT - 1f * textHeight, 1920, textHeight);
        host.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.player2.setAI(false);
                game.changeScreen(game.CONNECTFOUR);
                return true;
            }
        });
        stage.addActor(host);

        ImageButton join = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.join))));
        join.setBounds(edgesPadding, Games.MONITORHEIGHT - 3f * textHeight, join.getWidth() * scale, textHeight);
        join.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.player2.setAI(false);
                game.changeScreen(game.CONNECTFOUR);
                return true;
            }
        });
        //stage.addActor(join);

        ImageButton pvaibutton = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.vsai))));
        pvaibutton.setBounds(edgesPadding, Games.MONITORHEIGHT - 4.5f * textHeight, pvaibutton.getWidth() * scale, textHeight);
        pvaibutton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.player2.setAI(true);
                //TODO Implement minmax.
                game.changeScreen(game.CONNECTFOUR);
                return true;
            }
        });
        //stage.addActor(pvaibutton);

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
        viewport.update(width, height);
        cam.position.set(Games.MONITORWIDTH / 2f, Games.MONITORHEIGHT / 2f, 0);
        game.batch.setProjectionMatrix(cam.combined);
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
