package com.connectfour;

import java.io.*;

public class Board {

    protected byte[][] board;
    private int boardSizeX;
    private int boardSizeY;
    public Board(int boardSizeX, int boardSizeY) {
        board = new byte[boardSizeY][boardSizeX];
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;
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
        for (int y = 0; y < boardSizeY; y++) {
            for (int x = 0; x < boardSizeX - (inARow-1); x++) {
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
        for (int y = 0; y < boardSizeY - (inARow-1); y++) {
            for (int x = 0; x < boardSizeX; x++) {
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
        for (int y = 0; y < boardSizeY - (inARow-1); y++) {
            for (int x = 0; x < boardSizeX - (inARow-1); x++) {
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
        for (int y = (inARow-1); y <boardSizeY; y++) {
            for (int x = 0; x < boardSizeX - (inARow-1); x++) {
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
            for (int y = 0; y < boardSizeY; y++) {
                for (int x = 0; x < boardSizeX; x++) {
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
    public byte[][] cloneBoardArray(){
        return deepCopy(this.board);
    }
    private byte[][] deepCopy(byte[][] obj)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            // Beware, this can throw java.io.NotSerializableException
            // if any object inside obj is not Serializable
            oos.writeObject(obj);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            return (byte[][]) ois.readObject();
        }
        catch (  ClassNotFoundException /* Not sure */
                | IOException /* Never happens as we are not writing to disc */ e)
        {
            throw new RuntimeException(e); // Your own custom exception
        }
    }
}
