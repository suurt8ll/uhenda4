package com.connectfour.screens.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.Games;

public class EndScreen implements Screen {

    private final Games game;
    private final Outcome outcome;
    private final Camera cam;
    private final Viewport viewport;
    private Stage stage;
    private BitmapFontCache text;
    private BitmapFont font;

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
        //TODO Skaleeri ekraan paremini, praegu on nata imelik see.
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
                message = "YOU LOST!";
                break;
        }
        //font = new BitmapFont(new FileHandle("C:\\Users\\leoku\\Desktop\\UhendaNeli\\core\\assets\\fonts\\roboto-medium-1024px.fnt"));
        font = game.assetsLoader.manager.get(game.assetsLoader.robotoBlack);
        GlyphLayout layout = new GlyphLayout(font, message);
        text = new BitmapFontCache(font);
        //FIXME Tekst on mingitel kindlatel teadmata juhtudel valesti positsioneeritud ning scaletud.
        float textScale = (float) ((0.8 * Games.MONITORWIDTH) / layout.width);
        //muuda fondi suurust assetsloaderis init funktsioonis
        //font.getData().setScale(textScale);
        text.addText(message, (float) (Games.MONITORWIDTH * 0.1), 400);

        ImageButton newGame = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.newGame))));
        float imageScale = Games.MONITORWIDTH / (3 * newGame.getWidth());
        newGame.setBounds((float) (Games.MONITORWIDTH * 0.1), 400 - layout.height * textScale - newGame.getHeight() * imageScale - 50,
                newGame.getWidth() * imageScale, newGame.getHeight() * imageScale);
        newGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.changeScreen(game.CONNECTFOUR);
                return true;
            }
        });
        stage.addActor(newGame);

        ImageButton exit = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.exit))));
        exit.setBounds((float) (Games.MONITORWIDTH * 0.9 - exit.getWidth() * imageScale), 400 - layout.height * textScale - exit.getHeight() * imageScale - 50,
                exit.getWidth() * imageScale, exit.getHeight() * imageScale);
        exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.changeScreen(game.MAINMENU);
                return true;
            }
        });
        stage.addActor(exit);
        game.inputMultiplexer.addProcessor(stage);
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
        game.inputMultiplexer.removeProcessor(stage);
        stage.dispose();
        //kui managerist võtta ss ei pea enam disposima
        //font.dispose();
    }

    public enum Outcome {
        WIN,
        LOSE,
        DRAW
    }

}
