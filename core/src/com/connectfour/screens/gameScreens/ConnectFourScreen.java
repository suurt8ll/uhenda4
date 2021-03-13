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
import com.connectfour.Minimax;
import com.connectfour.server.Server;
import com.connectfour.server.TurnPacket;

public class ConnectFourScreen implements Screen {

    private final Games game;
    public Board board;
    public byte whoseTurn;
    private float aspectRatio;
    private OrthographicCamera cam;
    private float WORLD_SIZE_X, WORLD_SIZE_Y;
    private ExtendViewport vp;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private ImageButton[] imgButtonArr;
    public Server server = null;

    public ConnectFourScreen(final Games game) {
        this.game = game;
        imgButtonArr = new ImageButton[game.boardSizeX];
    }

    @Override
    public void show() {

        WORLD_SIZE_X = game.boardSizeX * 2 + (game.boardSizeX + 1) * game.spacing;
        WORLD_SIZE_Y = game.boardSizeY * 2 + (2 + game.spacing) + (game.boardSizeY + 1) * game.spacing;

        aspectRatio = (float) Games.MONITORHEIGHT / (float) Games.MONITORWIDTH;
        cam = new OrthographicCamera();
        vp = new ExtendViewport(WORLD_SIZE_X, WORLD_SIZE_Y, cam);
        cam.position.set(WORLD_SIZE_X / 2, WORLD_SIZE_Y / 2, 0);
        shapeRenderer = new ShapeRenderer();

        this.board = new Board(game.boardSizeX, game.boardSizeY);

        stage = new Stage(this.vp, this.game.batch);
        initButtons();
        if (game.player2.isAI()){
            //game.player2.minimax = new Minimax(game.player2.getId(), game.player1.getId(), (byte) 0, game.difficulty, board);
            whoseTurn = game.player1.getId();
            game.player2.minimax = new Minimax(game);
        }
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
        game.inputMultiplexer.removeProcessor(stage);
        stage.dispose();
        server.stop();
    }

    public void placeKetas(int x) {
        for (int y = 0; y < game.boardSizeY; y++) {
            if (board.getKettaState(x, y) == 0) {
                board.setKettaState(x, y, whoseTurn);
                //Kui veerg saab täis, siis kustuta nool.
                if (y == game.boardSizeY - 1) {
                    imgButtonArr[x].clear();
                    imgButtonArr[x] = null;
                }
                checkGameWin();
                doTurn();
                return;
            }
        }
    }

    private void buttonClick(InputEvent e) {
        if (whoseTurn == 1) {
            int mitmesVeerg = Integer.parseInt(e.getListenerActor().getName());
            placeKetas(mitmesVeerg);
            if (game.player2.isAI()) {
                Thread minMaxThread = new Thread(game.player2.minimax);
                minMaxThread.setName("MINMAXTHREAD");
                minMaxThread.start();
            } else {
                server.sendPacket(new TurnPacket(0, true, mitmesVeerg));
            }
        }
    }

    private void drawBoard(float spaceBetween, float radius) {
        int ringX = 0;
        int ringY = 0;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (float y = spaceBetween + radius; y <= game.boardSizeY * (2 * radius + spaceBetween); y += (2 * radius + spaceBetween)) {
            for (float x = spaceBetween + radius; x <= game.boardSizeX * (2 * radius + spaceBetween); x += (2 * radius + spaceBetween)) {
                if (board.getKettaState(ringX, ringY) == 0) {
                    shapeRenderer.setColor(Color.WHITE);
                } else if (board.getKettaState(ringX, ringY) == game.player1.getId()) {
                    shapeRenderer.setColor(game.player1.color);
                } else {
                    shapeRenderer.setColor(game.player2.color);
                }
                shapeRenderer.circle(x, y, radius, 100);
                ringX += 1;
            }
            ringX = 0;
            ringY += 1;
        }
        shapeRenderer.end();
    }

    private void initButtons() {
        Sprite arrowSprite = new Sprite((Texture) game.assetsLoader.manager.get(game.assetsLoader.arrowDownImg));
        int mitmes = 0;
        for (float x = game.spacing; x <= game.boardSizeX * (2 + game.spacing); x += (2 + game.spacing)) {
            ImageButton arrowDown = new ImageButton(new SpriteDrawable(arrowSprite));
            arrowDown.setBounds(x, WORLD_SIZE_Y - 2 - game.spacing, 2, 2);
            arrowDown.setName(Integer.toString(mitmes));
            imgButtonArr[mitmes] = arrowDown;

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

    private void doTurn(){
        if (whoseTurn == game.player1.getId()) {
            whoseTurn = game.player2.getId();
        } else {
            whoseTurn = game.player1.getId();
        }
    }

    private void checkGameWin(){
        EndScreen.Outcome outcome;
        switch (board.checkWin(4)) {
            case 0:
                outcome = EndScreen.Outcome.DRAW;
                break;
            case 1:
                outcome = EndScreen.Outcome.WIN;
                break;
            case 2:
                outcome = EndScreen.Outcome.LOSE;
                break;
            default:
                outcome = null;
        }
        if (outcome != null) Gdx.app.postRunnable(() -> game.setScreen(new EndScreen(game, outcome/*, cam, vp*/)));
    }
}
