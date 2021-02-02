package com.connectfour;

import com.badlogic.gdx.graphics.Color;

public class Player {
    public String name;
    public int id;
    public Color color;
    public boolean AI;
    public Player(String name, int id, Color color){
        this.name = name;
        this.id = id;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAI(boolean AI) {
        this.AI = AI;
    }

    public boolean isAI() {
        return AI;
    }

}
