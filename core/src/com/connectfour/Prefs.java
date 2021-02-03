package com.connectfour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {
    private final String SELECTED_GAME_NAME = "Selected_game_name";
    private final String MUSIC_VOLUME = "Music_volume";
    private final String SOUND_VOLUME = "Sound_volume";
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

    public float getMusicVolume() {
        return this.preferences.getFloat(this.MUSIC_VOLUME);
    }

    public float getSoundVolume() {
        return this.preferences.getFloat(this.SOUND_VOLUME);
    }

    public void setMusicVolume(float value) {
        this.preferences.putFloat(MUSIC_VOLUME, value);
    }
}
