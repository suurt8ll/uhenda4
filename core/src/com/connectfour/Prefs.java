package com.connectfour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;

public class Prefs {
    private final String MUSIC_VOLUME = "Music_volume";
    private final String SOUND_VOLUME = "Sound_volume";
    private final String PLAYER1_NAME = "Player1_name";
    private final String PLAYER2_NAME = "Player2_name";
    private final String BOARD_SIZE_X = "Board_size_x";
    private final String BOARD_SIZE_Y = "Board_size_y";
    private Preferences preferences;
    public Prefs(){
        this.preferences = Gdx.app.getPreferences("MyPrefs");
    }


    public float getMusicVolume() {
        return this.preferences.getFloat(this.MUSIC_VOLUME);
    }

    public float getSoundVolume() {
        return this.preferences.getFloat(this.SOUND_VOLUME);
    }

    public void setMusicVolume(float value) {
        this.preferences.putFloat(this.MUSIC_VOLUME, value);
    }

    public void setSoundVolume(float value) {
        this.preferences.putFloat(this.SOUND_VOLUME, value);
    }

    public void setPlayer1Name(String name) {
        this.preferences.putString(this.PLAYER1_NAME, name);
    }
    public void setPlayer2Name(String name) {
        this.preferences.putString(this.PLAYER2_NAME, name);
    }

    public void Boardx(int boardx) {
        this.preferences.putInteger(this.BOARD_SIZE_X, boardx);
    }
    public void Boardy(int boardy) {
        this.preferences.putInteger(this.BOARD_SIZE_Y, boardy);
    }

    public String getPlayer1Name(){
        return this.preferences.getString(this.PLAYER1_NAME);
    }
    public String getPlayer2Name(){
        return this.preferences.getString(this.PLAYER2_NAME);
    }
    public void save(){
        this.preferences.flush();
    }

    public String getPlayer1Color() {
        return this.preferences.getString(this.PLAYER2_NAME);
    }
}
