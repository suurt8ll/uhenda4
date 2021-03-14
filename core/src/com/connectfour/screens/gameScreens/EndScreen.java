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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.Games;
import com.connectfour.SimpleMenuScreenBuilder;

public class EndScreen implements Screen {

    private final Games game;
    private final Outcome outcome;
    private Stage stage;
    private final SimpleMenuScreenBuilder builder;

    private float menuWidth;
    private float menuHeight;

    public EndScreen(Games game, Outcome outcome) {
        this.game = game;
        this.outcome = outcome;
        builder = new SimpleMenuScreenBuilder(game);
    }

    @Override
    public void show() {
        //TODO Skaleeri ekraan paremini, praegu on nata imelik see.
        CharSequence message;
        switch (outcome) {
            case WIN:
                message = game.player1.name+" WON";
                break;
            case DRAW:
                message = "DRAW";
                break;
            default:
                message = game.player2.name+" WON";
                break;
        }
        //Label.LabelStyle style = game.skin.get(Label.LabelStyle.class);
        //Muudab skini yee yee ass fonti meie seksikaks Roboto fontiks.
        //Siin ei tohtinud muuta game.skin muutujas olevat fonti, pigem luua uus style.
        Label.LabelStyle style1 = new Label.LabelStyle();
        style1.font = game.assetsLoader.manager.get(game.assetsLoader.robotoBlack);
        Label text = new Label(message, style1);

        //TODO Uut mängu alustades tuleb natuke rohkem koodide, sest multiplayer. Praeguseks on nupp välja kommenteeritud.
        /*ImageButton newGame = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.newGame))));
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
        stage.addActor(newGame);*/

        //Viib lihtsalt tagasi MAINMENUSCREENILE.
        ImageButton exit = new ImageButton(new SpriteDrawable(new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.exit))));
        exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.changeScreen(game.MAINMENU);
                return true;
            }
        });

        builder.initStage(new Actor[]{text, exit}, 0.2f);
        stage = builder.getStage();
        game.inputMultiplexer.addProcessor(stage);

        menuWidth = builder.menuWidth;
        menuHeight = builder.menuHeight;
    }

    @Override
    public void render(float delta) {
        Gdx.gl30.glClearColor(game.backGroundColor.r, game.backGroundColor.g, game.backGroundColor.b, game.backGroundColor.a);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        stage.getCamera().position.set(menuWidth / 2f, menuHeight / 2f, 0);
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

    public enum Outcome {
        WIN,
        LOSE,
        DRAW
    }

}
