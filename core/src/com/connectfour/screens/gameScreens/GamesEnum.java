package com.connectfour.screens.gameScreens;

public enum GamesEnum {
    CONNECTFOUR("ConnectFour", 7,6);

    public int Boardsizex;
    public final String name;
    public int Boardsizey;

    GamesEnum(String name, int x, int y) {
        this.name = name;
        this.Boardsizex = x;
        this.Boardsizey = y;

    }

    public String getName() {
        return name;
    }
    public static GamesEnum gettype(String gamename){
        for (GamesEnum x: GamesEnum.values()) {
            if (x.name.equals(gamename)){
                return x;
            }
        }
        return values()[0];
    }

    public int getBoardsizex() {
        return Boardsizex;
    }

    public int getBoardsizey() {
        return Boardsizey;
    }

    public void setBoardsizex(int boardx) {
        this.Boardsizex = boardx;
    }

    public void setBoardsizey(int boardy) {
        this.Boardsizey = boardy;
    }
}
