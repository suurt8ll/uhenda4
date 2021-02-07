package com.connectfour.screens.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.connectfour.Board;
import com.connectfour.Games;

public class ConnectFourScreen implements Screen {

    private final Games game;
    public Board board;
    public byte whoseTurn;
    float aspectRatio;
    OrthographicCamera cam;
    ImageButton arrowDown;
    float WORLD_SIZE_X, WORLD_SIZE_Y;
    private ExtendViewport vp;
    private Stage stage;
    private ShapeRenderer shapeRenderer;

    public ConnectFourScreen(final Games game) {
        this.game = game;
        whoseTurn = 1;
    }

    @Override
    public void show() {

        WORLD_SIZE_X = game.boardSizeX * 2 + (game.boardSizeX + 1) * game.spacing;
        WORLD_SIZE_Y = game.boardSizeY * 2 + (2 + game.spacing) + (game.boardSizeY + 1) * game.spacing;

        aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        cam = new OrthographicCamera();
        vp = new ExtendViewport(WORLD_SIZE_X, WORLD_SIZE_Y, cam);
        vp.apply();
        cam.position.set(WORLD_SIZE_X / 2, WORLD_SIZE_Y / 2, 0);
        shapeRenderer = new ShapeRenderer();

        this.board = new Board(game.boardSizeX, game.boardSizeY);

        stage = new Stage(this.vp, this.game.batch);
        initButtons();

    }

    @Override
    public void render(float delta) {

        Gdx.gl30.glClearColor(game.backGroundColor.r, game.backGroundColor.g, game.backGroundColor.b, game.backGroundColor.a);
        Gdx.gl30.glClear(GL30.GL_COLOR_BUFFER_BIT);


        shapeRenderer.setProjectionMatrix(cam.combined);
        drawBoard(game.spacing, 1);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
        cam.position.set(WORLD_SIZE_X / 2, WORLD_SIZE_Y / 2, 0);
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

    private void buttonClick(InputEvent e) {
        int mitmes = Integer.parseInt(e.getListenerActor().getName());
        for (int y = 1; y <= game.boardSizeY; y++) {
            if (board.getKettaState(mitmes, y) == 0) {
                board.setKettaState(mitmes, y, whoseTurn);
                System.out.println(board.checkWin(4));
                if (whoseTurn == 1) {
                    whoseTurn = 2;
                } else {
                    whoseTurn = 1;
                }
                if (y == game.boardSizeY) e.getListenerActor().clear();
                //board.printboard();
                return;
            }
        }
    }

    private void drawBoard(float spaceBetween, float radius) {
        int ringX = 1;
        int ringY = 1;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (float y = spaceBetween + radius; y <= game.boardSizeY * (2 * radius + spaceBetween); y += (2 * radius + spaceBetween)) {
            for (float x = spaceBetween + radius; x <= game.boardSizeX * (2 * radius + spaceBetween); x += (2 * radius + spaceBetween)) {
                if (board.getKettaState(ringX, ringY) == 0) {
                    shapeRenderer.setColor(Color.WHITE);
                } else if (board.getKettaState(ringX, ringY) == 1) {
                    shapeRenderer.setColor(game.player1.color);
                } else {
                    shapeRenderer.setColor(game.player2.color);
                }
                shapeRenderer.circle(x, y, radius, 100);
                ringX += 1;
            }
            ringX = 1;
            ringY += 1;
        }
        shapeRenderer.end();
    }

    private void initButtons() {
        Sprite arrowSprite = new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.arrowDownImg));
        int mitmes = 1;
        for (float x = game.spacing; x <= game.boardSizeX * (2 + game.spacing); x += (2 + game.spacing)) {
            arrowDown = new ImageButton(new SpriteDrawable(arrowSprite));
            arrowDown.setBounds(x, WORLD_SIZE_Y - 2 - game.spacing, 2, 2);
            arrowDown.setName(Integer.toString(mitmes));

            arrowDown.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    buttonClick(event);
                    return true;
                }
            });

            stage.addActor(arrowDown);
            mitmes += 1;
        }
        game.inputMultiplexer.addProcessor(stage);
    }
}
