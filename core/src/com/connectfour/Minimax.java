package com.connectfour;

public class Minimax implements Runnable{
    private byte playerid;
    private byte aiid;
    private byte emptyid;
    private int difficulty;
    public int bestMove;
    private Board board;
    public boolean threadrunning;

    public Minimax(byte aiid, byte playerid, byte emptyid, int difficulty, Board board) {
        this.aiid = aiid;
        this.playerid = playerid;
        this.emptyid = emptyid;
        this.difficulty = difficulty;
        this.board = board;
        this.threadrunning = false;
    }
    public int findBestMove(Board board){
        Board tempBoard = new Board(board.board[0].length,board.board.length);
        tempBoard.board = board.cloneBoardArray();
        board = tempBoard;

        int bestscore = -100000;
        int move = 0;
        byte[][] b = board.cloneBoardArray();
        board.board = b;

        int[] posToTry = new int[b[0].length];
        for (int i = 0; i < b[0].length; i++) {
            posToTry[i] = 0;
        }
        for (int i = 0; i < b[0].length; i++) {
            int x = centerXpicker(board, posToTry);
            if (x !=-1){
                int y = board.getYwithX(x);
                if (y!=-1){
                    posToTry[x] = 1;
                    b[y][x]=this.aiid;
                    int score = minimax2(board,difficulty,false, -10000, 10000);

                    b[y][x]=emptyid;
                    if (score>bestscore){
                        bestscore = score;
                        move = x;
                    }
                }
            }
        }
        return move;
    }
    public int minimax2(Board board, int depth, boolean isMaximizing, int alpha, int beta){
        byte[][] b = board.getBoard();
        if (depth == 0|| board.checkWin(4)!=-1){
            int result = scorePosition(board);
            return result;
        }
        if (isMaximizing){
            int bestscore = -1000;
            for (int x = 0; x < b[0].length; x++) {
                int y = board.getYwithX(x);
                if (y!=-1){
                    b[y][x] = aiid;
                    int score = minimax2(board, depth-1, false, alpha, beta)-1;
                    b[y][x] = emptyid;
                    alpha = Math.max(alpha,score);
                    bestscore = Math.max(score,bestscore);
                    if (beta<=alpha){
                        x = b[0].length;
                    }
                }
            }
            return bestscore;
        }else {
            int bestscore = 1000;
            for (int x = 0; x < b[0].length; x++) {
                int y = board.getYwithX(x);
                if (y!=-1){
                    b[y][x] = playerid;
                    int score = minimax2(board, depth-1, true, alpha, beta)+1;
                    b[y][x] = emptyid;
                    beta = Math.min(beta,score);
                    bestscore = Math.min(score,bestscore);
                    if (beta<=alpha){
                        x = b[0].length;
                    }
                }
            }
            return bestscore;
        }
    }

    public int scorePosition(Board board){
        int result = board.checkWin(4);
        if (result == aiid){
            return 1000;
        }else if (result == playerid){
            return -1000;
        }
        result = board.checkWin(3);
        if (result==aiid){
            return 900;
        }else if (result == playerid){
            return -900;
        }
        return 0;
    }
    public int centerXpicker(Board board, int[] openPositions){
        byte[][] b = board.getBoard();
        int maxy = b.length-1;
        int x = b.length/2;
        boolean goleft = true;
        for (int i = 1; i < b[0].length+1; i++) {
            if (openPositions[x]==0 && b[maxy][x] == 0){
                return x;
            }
            if (goleft){
                x-=i;
                goleft = false;
            }else {
                x+=i;
                goleft = true;
            }
        }
        return -1;
    }

    @Override
    public void run() {
        threadrunning = true;
        bestMove = findBestMove(board);
        board.setKettaState(bestMove,board.getYwithX(bestMove), aiid);
        threadrunning = false;
    }
}
