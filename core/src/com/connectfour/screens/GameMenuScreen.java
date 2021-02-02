package com.connectfour.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.connectfour.Games;

public class GameMenuScreen implements Screen {
    Games game;
    private Stage stage;
    public GameMenuScreen(final Games game){
        this.game = game;

    }
    @Override
    public void show() {
        stage = new Stage(game.viewport,game.batch);
        ImageButton pvpbutton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pvp.png")))));
        pvpbutton.setPosition(stage.getCamera().viewportWidth/2-pvpbutton.getWidth()/2, stage.getCamera().viewportHeight/2-pvpbutton.getHeight()/2);
        pvpbutton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.player2.setAI(false);
                game.selectedGameType.run(game);
                return true;
            }
        });
        ImageButton pvaibutton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pvai.png")))));
        pvaibutton.setPosition(stage.getCamera().viewportWidth/2-pvaibutton.getWidth()/2, stage.getCamera().viewportHeight/2+pvaibutton.getHeight()/2);
        pvaibutton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.player2.setAI(true);
                game.selectedGameType.run(game);
                return true;
            }
        });
        Table table = new Table();
        table.setFillParent(true);
        table.add(pvpbutton).pad(10);
        table.row();
        table.add(pvaibutton);
        stage.addActor(table);
        game.inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl30.glClearColor(1, 1, 1, 1);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
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
