package com.connectfour.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.connectfour.Games;
import java.util.Random;

public class Host extends Server implements Runnable {

    private final ServerSocket server;

    public Host(Games game, int port) {
        super(game);
        server = Gdx.net.newServerSocket(Net.Protocol.TCP, port, null);
    }

    @Override
    public void run() {
        running = true;
        //Loob ühendused
        System.out.println("[SERVER] Wating for opponent...");
        super.socket = server.accept(null);
        System.out.printf("[SERVER] Opponent connected from %s! \n", super.socket.getRemoteAddress());
        //Teeb coinflipi, et selgitada, kes alustab. 1 = host, 2 = vastane
        boolean opponentsTurn;
        Random random = new Random();
        opponentsTurn = random.nextBoolean();
        TurnPacket firstTurn = new TurnPacket(1, opponentsTurn, 0);
        //Loob streamid
        super.initStreams();
        System.out.println("[SERVER] Streams with opponent have been made.");
        //Annab vastasele teada, kes esimesena alustab.
        super.sendPacket(firstTurn);
        //Kas mina alustan või mitte?
        if (opponentsTurn) {
            super.game.CONNECTFOUR.whoseTurn = 2;
        } else {
            super.game.CONNECTFOUR.whoseTurn = 1;
        }
        //Server töötab -> alusta mänguga
        Gdx.app.postRunnable(() -> super.game.changeScreen(super.game.CONNECTFOUR));
        //Thread lõpetab töö
        super.stop();
    }
}
