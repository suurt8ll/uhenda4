package com.connectfour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {
    private final String SELECTED_GAME_NAME = "Selected game name";
    private Preferences preferences;
    public Prefs(){
        this.preferences = Gdx.app.getPreferences("MyPrefs");
    }
    public Preferences getPreferences() {
        return preferences;
    }
    public String getSelectedGameName(){
        return this.preferences.getString(this.SELECTED_GAME_NAME);
    }
}
