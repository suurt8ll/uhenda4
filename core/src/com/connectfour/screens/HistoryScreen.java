package com.connectfour.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.connectfour.Games;

import java.util.Arrays;

public class HistoryScreen implements Screen {
    private final Games game;
    private Stage stage;
    public HistoryScreen(final Games game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(game.viewport,game.batch);
        Label.LabelStyle style = game.skin.get(Label.LabelStyle.class);

        final TextButton exit = new TextButton("Exit",game.skin);
        exit.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.changeScreen(game.MAINMENU);
                return true;
            }
        });
        String historyString = game.prefs.getHistory();
        String[] strings = historyString.split(";");
        String s = "Winners:\n";
        int counter = 1;
        for (int i = strings.length-1; i >=0; i--) {
            s+=counter+". "+strings[i]+"\n";
            counter++;
        }
        final Label label = new Label(s, game.skin);

        Table table = new Table();
        table.setFillParent(true);
        table.add(label);
        table.row();
        table.add(exit).height(50).width(200).padTop(30);
        stage.addActor(table);
        game.inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void render(float v) {
        Gdx.gl30.glClearColor(0, 0, 0, 1);
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
