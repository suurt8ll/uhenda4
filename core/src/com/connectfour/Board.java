package com.connectfour;

public class Board {

    protected byte[][] board;

    public Board(int boardSizeX, int boardSizeY) {
        board = new byte[boardSizeY][boardSizeX];
    }

    /**Mängulaua koordinaadid algavad ülevalt vasakust nurgast, esimene ketas on näiteks (1, 1), teine (2, 1) jne...*/
    public byte getKettaState(int boardX, int boardY) {
        return board[boardY][boardX];
    }

    /**Mängulaua koordinaadid algavad ülevalt vasakust nurgast, esimene ketas on näiteks (1, 1), teine (2, 1) jne...*/
    public void setKettaState(int boardX, int boardY, byte state) {
        board[boardY][boardX] = state;
    }

    public byte[][] getBoard() {
        return board;
    }

    /** Väljastab konsooli mängulaua maatriksi.*/
    public void printboard() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                System.out.printf("%s ", board[y][x]);
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    public int checkWin(int inARow) {
        int voitja = -1;
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length - (inARow-1); x++) {
                for (int playerid = 1; playerid < 3; playerid++) {
                    boolean praeguneCheck = true;
                    for (int inRow = 0; inRow < inARow; inRow++) {
                        if (board[y][x + inRow] != playerid) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if (praeguneCheck) {
                        voitja = playerid;
                    }
                }
            }
        }
        for (int y = 0; y < board.length - (inARow-1); y++) {
            for (int x = 0; x < board[0].length; x++) {
                for (int playerid = 1; playerid < 3; playerid++) {
                    boolean praeguneCheck = true;
                    for (int inRow = 0; inRow < inARow; inRow++) {
                        if (board[y + inRow][x] != playerid) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if (praeguneCheck) {
                        voitja = playerid;
                    }
                }
            }
        }
        for (int y = 0; y < board.length - (inARow-1); y++) {
            for (int x = 0; x < board[0].length - (inARow-1); x++) {
                for (int playerid = 1; playerid < 3; playerid++) {
                    boolean praeguneCheck = true;
                    for (int inRow = 0; inRow < inARow; inRow++) {
                        if (board[y + inRow][x + inRow] != playerid) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if (praeguneCheck) {
                        voitja = playerid;
                    }
                }
            }
        }
        for (int y = (inARow-1); y < board.length; y++) {
            for (int x = 0; x < board[0].length - (inARow-1); x++) {
                for (int playerid = 1; playerid < 3; playerid++) {
                    boolean praeguneCheck = true;
                    for (int inRow = 0; inRow < inARow; inRow++) {
                        if (board[y - inRow][x + inRow] != playerid) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if (praeguneCheck) {
                        voitja = playerid;
                    }
                }
            }
        }
        if (voitja == -1) {
            loop:
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[0].length; x++) {
                    if (board[y][x] == 0) {
                        voitja = -1;
                        break loop;
                    } else {
                        voitja = 0;
                    }
                }
            }
        }
        return voitja;
    }
    public int getYwithX(int x){
        for (int y = 0; y < board.length; y++) {
            if (board[y][x] == 0) {
                return y;
            }
        }
        return -1;
    }
}
