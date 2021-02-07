package com.connectfour.screens.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.Games;

public class EndScreen implements Screen {

    private Games game;
    private Outcome outcome;
    private Camera cam;
    private Viewport viewport;
    private Stage stage;
    private BitmapFontCache text;

    public EndScreen(Games game, Outcome outcome) {
        this.game = game;
        this.outcome = outcome;
        this.cam = game.viewport.getCamera();
        this.viewport = game.viewport;
    }

    public EndScreen(Games game, Outcome outcome, Camera cam, Viewport viewport) {
        this.game = game;
        this.outcome = outcome;
        this.cam = cam;
        this.viewport = viewport;
    }

    @Override
    public void show() {
        stage = new Stage(viewport, game.batch);
        CharSequence message;
        switch (outcome) {
            case WIN:
                message = "YOU WON!";
                break;
            case DRAW:
                message = "DRAW";
                break;
            default:
                message = "YOU LOST! SHITASS!";
                break;
        }
        text = new BitmapFontCache(new BitmapFont(new FileHandle("C:\\Users\\leoku\\Desktop\\UhendaNeli\\core\\assets\\fonts\\roboto-medium-1px.fnt")));
        text.addText(message, 0, 500);
    }

    @Override
    public void render(float delta) {
        Gdx.gl30.glClearColor(game.backGroundColor.r, game.backGroundColor.g, game.backGroundColor.b, game.backGroundColor.a);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        text.draw(game.batch);
        game.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        stage.dispose();
    }

    public enum Outcome {
        WIN,
        LOSE,
        DRAW
    }

}
