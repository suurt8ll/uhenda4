package com.connectfour.server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.connectfour.Games;
import java.io.IOException;

public class Client extends Server implements Runnable {

    public Client(Games game, String hostIpAdress, int hostPort) {
        super(game);
        try {
            System.out.printf("Connecting with %s:%s \n", hostIpAdress, hostPort);
            super.socket = Gdx.net.newClientSocket(Net.Protocol.TCP, hostIpAdress, hostPort, null);
        } catch (GdxRuntimeException e) {
            System.out.println("[CONSOLE] Ühendust ei õnnestunud luua!");
        }
    }

    @Override
    public void run() {
        if (super.socket == null) return;
        super.running = true;
        //Loob serveriga streamid
        super.initStreams();
        System.out.println("[OPPONENT] Streams with remote host created!");
        //Ootab esimese packeti ära, et teada saada, kes alustab
        TurnPacket firstTurn = null;
        try {
            firstTurn = (TurnPacket) super.inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Kui kõik läks hästi, vaata, kas mina alustan
        assert firstTurn != null;
        if (firstTurn.yourTurn) {
            System.out.println("[OPPONENT] Mina alustan!");
            super.game.CONNECTFOUR.whoseTurn = 1;
        } else {
            super.game.CONNECTFOUR.whoseTurn = 2;
        }
        //Serveriga ühendus loodud -> alusta mänguga
        super.game.CONNECTFOUR.server = this;
        Gdx.app.postRunnable(() -> super.game.changeScreen(super.game.CONNECTFOUR));
        //Alustab ühenduse kuulamist
        while (super.running) {
            super.recievePacket();
        }
        //Thread lõpetab töö
        super.stop();
    }
}
