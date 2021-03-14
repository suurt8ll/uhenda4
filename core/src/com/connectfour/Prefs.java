package com.connectfour;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

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
    private final String SPACING = "Spacing";
    private final String DIFFICULTY = "Difficulty";
    private final String HISTORY = "History";
    private Preferences preferences;

    public Prefs(){
        this.preferences = Gdx.app.getPreferences("ConnectFourPrefs");
    }

    public void save(){
        this.preferences.flush();
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
        boolean toSave = false;
        if (!this.preferences.get().containsKey(this.MUSIC_VOLUME)){
            setMusicVolume(0.5f);
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.SOUND_VOLUME)){
            setSoundVolume(0.5f);
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.PLAYER1_NAME)){
            setPlayer1Name("Player1");
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.PLAYER2_NAME)){
            setPlayer2Name("Computer");
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.BOARD_SIZE_X)){
            setBoardx(7);
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.BOARD_SIZE_Y)){
            setBoardy(6);
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.PLAYER1_COLOR)){
            setPlayer1Color("09ff00ff");
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.PLAYER2_COLOR)){
            setPlayer2Color("ff0d00ff");
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.BACKGROUND_COLOR)){
            setBackgroundColor("0065ffff");
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.SPACING)){
            setSpacing(0.2f);
            toSave = true;
        }

        if (!this.preferences.get().containsKey(this.DIFFICULTY)){
            setDifficulty(3);
            toSave = true;
        }
        if (!this.preferences.get().containsKey(this.HISTORY)){
            setHistory("");
            toSave= true;
        }
        if (getPlayer1Color().equals("")){
            setPlayer1Color("09ff00ff");
            toSave = true;
        }
        if (getPlayer2Color().equals("")){
            setPlayer2Color("ff0d00ff");
            toSave = true;
        }
        if (getBackgroundColor().equals("")){
            setBackgroundColor("0065ffff");
            toSave = true;
        }
        if (toSave){
            save();
        }
    }
    public int getBoardx(){
        return this.preferences.getInteger(this.BOARD_SIZE_X);
    }
    public int getBoardy(){
        return this.preferences.getInteger(this.BOARD_SIZE_Y);
    }
    public void setSpacing(float spacing){
        this.preferences.putFloat(this.SPACING, spacing);
    }
    public float getSpacing(){
        return this.preferences.getFloat(this.SPACING);
    }
    public void setDifficulty(int difficulty){
        this.preferences.putInteger(this.DIFFICULTY, difficulty);
    }
    public int getDifficulty(){
        return this.preferences.getInteger(this.DIFFICULTY);
    }
    public String getHistory(){
        return this.preferences.getString(this.HISTORY);
    }
    public void addHistory(String s){
        String string = getHistory();

        String[] stringList = string.split(";");
        if (stringList.length>9){
            String newString = "";
            for (int i = 1; i < stringList.length; i++) {
                newString += stringList[i]+";";
            }
            string = newString;
        }
        string+=s+";";
        this.preferences.putString(this.HISTORY,string);
        save();
    }
    public void setHistory(String s){
        this.preferences.putString(this.HISTORY, s);
    }
}
