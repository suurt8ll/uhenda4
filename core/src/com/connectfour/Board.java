package com.connectfour;

public class Board {
    protected int[][] board;
    protected float size;
    public Board(int x, int y){
        board = new int[x][y];
    }
    public void init(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j]=0;
            }
        }
    }
    public void setSize(float size) {
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void printboard(){
        for (int i = board[0].length-1; i >= 0; i--) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[j][i]);
            }
            System.out.println("");
        }
    }
    public int checkWin(int inARow){
        int voitja = -1;
        for (int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length-3; j++){
                for(int l = 1; l < 3; l++) {
                    boolean praeguneCheck = true;
                    for (int k = 0; k < inARow; k++) {
                        if (board[i][j + k] != l) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if(praeguneCheck){
                        voitja = l;
                    }
                }
            }
        }
        for(int i = 0; i < board.length-3; i++){
            for(int j = 0; j < board[0].length; j++){
                for(int l = 1; l < 3; l++) {
                    boolean praeguneCheck = true;
                    for (int k = 0; k < inARow; k++) {
                        if (board[i + k][j] != l) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if(praeguneCheck){
                        voitja = l;
                    }
                }
            }
        }
        for(int i = 0; i < board.length-3; i++){
            for(int j = 0; j < board[0].length-3; j++){
                for(int l = 1; l < 3; l++) {
                    boolean praeguneCheck = true;
                    for (int k = 0; k < inARow; k++) {
                        if (board[i + k][j + k] != l) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if(praeguneCheck){
                        voitja = l;
                    }
                }
            }
        }
        for(int i = 3; i < board.length; i++){
            for(int j = 0; j < board[0].length-3; j++){
                for(int l = 1; l < 3; l++) {
                    boolean praeguneCheck = true;
                    for (int k = 0; k < inARow; k++) {
                        if (board[i - k][j + k] != l) {
                            praeguneCheck = false;
                            break;
                        }
                    }
                    if(praeguneCheck){
                        voitja = l;
                    }
                }
            }
        }
        if (voitja == -1){
            loop:
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j]==0){
                        voitja = -1;
                        break loop;
                    }else {
                        voitja = 0;
                    }
                }
            }
        }
        return voitja;
    }
}
