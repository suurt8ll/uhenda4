package com.connectfour.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
//import com.connectfour.ColorPicker;
import com.connectfour.Games;
import com.connectfour.NumberTextFieldFilter;
import com.connectfour.screens.gameScreens.GamesEnum;
//import com.connectfour.testacor;

public class SettingsScreen implements Screen {
    private Games game;
    private Stage stage;

    public SettingsScreen(final Games game){
        this.game = game;
        stage = new Stage(game.viewport,game.batch);
    }

    @Override
    public void show() {
        final TextField nameTextField = new TextField(game.player1.getName(), game.skin);
        final TextField nameTextField2 = new TextField(game.player2.getName(), game.skin);
        final TextField boardsizeX = new TextField(String.valueOf(GamesEnum.gettype(game.getPreferences().getSelectedGameName()).getBoardsizex())  , game.skin);
        final TextField boardsizeY = new TextField(String.valueOf(GamesEnum.gettype(game.getPreferences().getSelectedGameName()).getBoardsizey()), game.skin);
        final SelectBox<String> selectBox = new SelectBox<>(game.skin);
        final TextButton applybutton = new TextButton("Apply",game.skin);
        final Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, game.skin);
        final Slider volumeSoundSlider = new Slider( 0f, 1f, 0.1f,false, game.skin);
        //ColorPicker player1ColorPicker = new ColorPicker(150,20, game.player1.getColor(),game.getPreferences().getPlayer1Color());
        //ColorPicker player2ColorPicker = new ColorPicker(150,20, game.player2.getColor(),game.getPreferences().getPlayer2Color());
        volumeMusicSlider.setValue(game.getPreferences().getMusicVolume());
        volumeSoundSlider.setValue(game.getPreferences().getSoundVolume());
        boardsizeX.setTextFieldFilter(new NumberTextFieldFilter());
        boardsizeY.setTextFieldFilter(new NumberTextFieldFilter());
        String[] values = new String[GamesEnum.values().length];//{GamesEnum.CONNECTFOUR.getName(), GamesEnum.GOMOKU.getName()};
        for (int i = 0; i < GamesEnum.values().length; i++) {
            values[i] = GamesEnum.values()[i].getName();
        }
        selectBox.setItems(values);
        selectBox.setSelected(game.getPreferences().getSelectedGameName());
        selectBox.setPosition(0, game.ScreenHeight-selectBox.getHeight());
        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GamesEnum type = GamesEnum.gettype(selectBox.getSelected());
                boardsizeX.setText(String.valueOf(type.getBoardsizex()));
                boardsizeY.setText(String.valueOf(type.getBoardsizey()));

            }
        });
        /*
        applybutton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String name1 = nameTextField.getText();
                String name2 = nameTextField2.getText();
                int boardx = Integer.parseInt(boardsizeX.getText());
                int boardy = Integer.parseInt(boardsizeY.getText());
                game.player1.setName(name1);
                game.player2.setName(name2);
                game.selectedGameType = GamesEnum.gettype(selectBox.getSelected());
                game.selectedGameType.setBoardsizex(boardx);
                game.selectedGameType.setBoardsizey(boardy);
                game.getPreferences().setMusicVolume(volumeMusicSlider.getValue());
                game.getPreferences().setSoundVolume(volumeSoundSlider.getValue());
                game.getPreferences().setPlayer1Name(name1);
                game.getPreferences().setPlayer2Name(name2);
                if (GamesEnum.gettype(selectBox.getSelected())==GamesEnum.CONNECTFOUR){
                    game.getPreferences().setConnectfourBoardx(boardx);
                    game.getPreferences().setConnectfourBoardy(boardy);
                }
                if (GamesEnum.gettype(selectBox.getSelected())==GamesEnum.GOMOKU){
                    game.getPreferences().setGomokuBoardx(boardx);
                    game.getPreferences().setGomokuBoardy(boardy);
                }
                game.getPreferences().setSelectedGameName(game.selectedGameType.getName());
                game.player1.setColor(player1ColorPicker.getCurrentColor());
                game.player2.setColor(player2ColorPicker.getCurrentColor());
                game.getPreferences().setPlayer1Color(player1ColorPicker.getStringColorHex());
                game.getPreferences().setPlayer2Color(player2ColorPicker.getStringColorHex());
                game.changeScreen(Games.MENU);
                return true;
            }
        });

         */
        /*
        Table table = new Table();
        table.setFillParent(true);
        table.add(new Label("Player1: ", game.skin));
        table.add(nameTextField);
        table.add(selectBox).width(150);
        table.row();
        table.add(new Label("Player2: ", game.skin));
        table.add(nameTextField2);
        table.row();
        table.add(new Label("Board size x: ", game.skin));
        table.add(boardsizeX);
        table.row();
        table.add(new Label("Board size y: ", game.skin));
        table.add(boardsizeY);
        table.row();
        table.add(new Label("Music volume: ",game.skin));
        table.add(volumeMusicSlider);
        table.row();
        table.add(new Label("Sound volume: ",game.skin));
        table.add(volumeSoundSlider);
        table.row();
        table.add(new Label("Player1 color: ",game.skin));
        table.add(player1ColorPicker);
        table.row();
        table.add(new Label("Player2 color: ",game.skin));
        table.add(player2ColorPicker);
        table.row();
        table.add(applybutton).colspan(3).height(100).width(300).padTop(30);
        stage.addActor(table);
        player1ColorPicker.setPosition(100,100);
        game.inputMultiplexer.addProcessor(stage);
         */
    }

    @Override
    public void render(float delta) {
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
