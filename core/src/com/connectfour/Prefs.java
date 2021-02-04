package com.connectfour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import java.util.Map;

public class Prefs {

    private final String MUSIC_VOLUME = "Music_volume";
    private final String SOUND_VOLUME = "Sound_volume";
    private final String PLAYER1_NAME = "Player1_name";
    private final String PLAYER2_NAME = "Player2_name";
    private final String BOARD_SIZE_X = "Board_size_x";
    private final String BOARD_SIZE_Y = "Board_size_y";
    private final String PLAYER1_COLOR = "Player1_color";
    private final String PLAYER2_COLOR = "Player2_color";
    private final String BACKGROUND_COLOR = "Background_color";
    private Preferences preferences;
    public Prefs(){
        this.preferences = Gdx.app.getPreferences("ConnectFourPrefs");
    }

    public void save(){
        this.preferences.flush();
    }

    public void init(){
        if (this.preferences.getString(this.PLAYER1_NAME).equals("")){
            setMusicVolume(1);
            setSoundVolume(1);
            setPlayer1Name("Player1");
            setPlayer2Name("Player2");
            setBoardx(7);
            setBoardy(6);
            setPlayer1Color("17ff00ff");
            setPlayer2Color("f5ff00ff");
            setBackgroundColor("00ff00ff");
            save();
        }
    }

    public int getBoardx(){
        return this.preferences.getInteger(this.BOARD_SIZE_X);
    }

    public int getBoardy(){
        return this.preferences.getInteger(this.BOARD_SIZE_Y);
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

    public void setBoardx(int boardx) {
        this.preferences.putInteger(this.BOARD_SIZE_X, boardx);
    }
    public void setBoardy(int boardy) {
        this.preferences.putInteger(this.BOARD_SIZE_Y, boardy);
    }

    public String getPlayer1Name(){
        return this.preferences.getString(this.PLAYER1_NAME);
    }
    public String getPlayer2Name(){
        return this.preferences.getString(this.PLAYER2_NAME);
    }

    public String getPlayer1Color() {
        return this.preferences.getString(this.PLAYER1_COLOR);
    }
    public String getPlayer2Color() {
        return this.preferences.getString(this.PLAYER2_COLOR);
    }
    public String getBackgroundColor() {
        return this.preferences.getString(this.BACKGROUND_COLOR);
    }

    public void setPlayer1Color(String hex){
        this.preferences.putString(this.PLAYER1_COLOR, hex);
    }
    public void setPlayer2Color(String hex){
        this.preferences.putString(this.PLAYER2_COLOR, hex);
    }
    public void setBackgroundColor(String hex){
        this.preferences.putString(this.BACKGROUND_COLOR, hex);
    }
    public void init(){
        if (this.preferences.get().isEmpty()){
            setMusicVolume(0.5f);
            setSoundVolume(0.5f);
            setPlayer1Name("Player1");
            setPlayer2Name("Player2");
            setBoardx(7);
            setBoardy(6);
            save();
        }
        if (getPlayer1Color().equals("")){
            setPlayer1Color("09ff00ff");
            save();
        }
        if (getPlayer2Color().equals("")){
            setPlayer2Color("ff0d00ff");
            save();
        }
        if (getBackgroundColor().equals("")){
            setBackgroundColor("0065ffff");
            save();
        }
    }
    public int getBoardx(){
        return this.preferences.getInteger(this.BOARD_SIZE_X);
    }
    public int getBoardy(){
        return this.preferences.getInteger(this.BOARD_SIZE_Y);
    }
}
