package com.connectfour;

public class Minimax {
    public Board board;
    public byte[][] b;
    public byte playerid;
    public byte aiid;
    public byte emptyid;

    public Minimax(Board board) {
        this.board = board;
        this.b = board.getBoard();
        this.aiid = 2;
        this.playerid = 1;
        this.emptyid = 0;
    }
    public int minimax1(boolean ismaximizing, int alpha, int beta, int depth){
        int winner = board.checkWin(4);
        int score = 0;
        if (winner!=-1){
            return score;
        }
        if (depth==0){
            return 0;
        }
        if (ismaximizing){
            int bestscore = -1000;
            for (int x = 0; x < b.length; x++) {
                for (int y = 0; y < b.length; y++) {
                    if (b[y][x]==emptyid){
                        b[y][x]=this.aiid;
                        score = minimax1(false, alpha, beta, depth-1)-1;
                        b[y][x]=emptyid;
                        bestscore = Math.max(bestscore,score);
                        alpha = Math.max(alpha,score);
                        if (beta<=alpha){
                            x = b.length;
                            y = b.length;
                        }
                    }
                }
            }
            return bestscore;
        }else {
            int bestscore = 1000;
            for (int x = 0; x < b.length; x++) {
                for (int y = 0; y < b.length; y++) {
                    if (b[y][x]==emptyid){
                        b[y][x]=this.playerid;
                        score = minimax1(true, alpha, beta, depth-1)-1;
                        b[y][x]=emptyid;
                        bestscore = Math.min(bestscore,score);
                        beta = Math.min(alpha,score);
                        if (beta<=alpha){
                            x = b.length;
                            y = b.length;
                        }
                    }
                }
                return bestscore;
            }
        }
        return 0;
    }

    /*
    function minimax(node, depth, maximizingPlayer) is
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞
        for each child of node do
            value := max(value, minimax(child, depth − 1, FALSE))
        return value
    else (* minimizing player *)
        value := +∞
        for each child of node do
            value := min(value, minimax(child, depth − 1, TRUE))
        return value
     */
}
