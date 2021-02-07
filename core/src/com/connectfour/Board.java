package com.connectfour;

public class Board {

    protected byte[][] board;

    public Board(int boardSizeX, int boardSizeY) {
        board = new byte[boardSizeY][boardSizeX];
    }

    /**Mängulaua koordinaadid algavad ülevalt vasakust nurgast, esimene ketas on näiteks (1, 1), teine (2, 1) jne...*/
    public byte getKettaState(int boardX, int boardY) {
        return board[boardY - 1][boardX - 1];
    }

    /**Mängulaua koordinaadid algavad ülevalt vasakust nurgast, esimene ketas on näiteks (1, 1), teine (2, 1) jne...*/
    public void setKettaState(int boardX, int boardY, byte state) {
        board[boardY - 1][boardX - 1] = state;
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
            for (int x = 0; x < board[0].length - 3; x++) {
                for (int l = 1; l < 3; l++) {
                    boolean praeguneCheck = true;
                    for (int k = 0; k < inARow; k++) {
                        if (board[y][x + k] != l) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if (praeguneCheck) {
                        voitja = l;
                    }
                }
            }
        }
        for (int y = 0; y < board.length - 3; y++) {
            for (int x = 0; x < board[0].length; x++) {
                for (int l = 1; l < 3; l++) {
                    boolean praeguneCheck = true;
                    for (int k = 0; k < inARow; k++) {
                        if (board[y + k][x] != l) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if (praeguneCheck) {
                        voitja = l;
                    }
                }
            }
        }
        for (int y = 0; y < board.length - 3; y++) {
            for (int x = 0; x < board[0].length - 3; x++) {
                for (int l = 1; l < 3; l++) {
                    boolean praeguneCheck = true;
                    for (int k = 0; k < inARow; k++) {
                        if (board[y + k][x + k] != l) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if (praeguneCheck) {
                        voitja = l;
                    }
                }
            }
        }
        for (int y = 3; y < board.length; y++) {
            for (int x = 0; x < board[0].length - 3; x++) {
                for (int l = 1; l < 3; l++) {
                    boolean praeguneCheck = true;
                    for (int k = 0; k < inARow; k++) {
                        if (board[y - k][x + k] != l) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if (praeguneCheck) {
                        voitja = l;
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
}
