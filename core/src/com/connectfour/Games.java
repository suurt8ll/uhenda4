package com.connectfour;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.connectfour.screens.gameScreens.GamesEnum;

public class Games {
    public Viewport viewport;
    public Batch batch;
    public Player player1;
    public Skin skin;
    public Player player2;
    public GamesEnum selectedGameType;

    public Object getPreferences() {
    }
}
