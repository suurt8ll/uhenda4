package server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.connectfour.Games;
import java.io.IOException;

public class Client extends Server implements Runnable {

    public Client(Games game, String hostIpAdress, int hostPort) {
        super(game);
        super.socket = Gdx.net.newClientSocket(Net.Protocol.TCP, hostIpAdress, hostPort, null);
    }

    @Override
    public void run() {
        running = true;
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
            super.game.CONNECTFOUR.whoseTurn = 1;
        } else {
            super.game.CONNECTFOUR.whoseTurn = 2;
        }
        //Serveriga ühendus loodud -> alusta mänguga
        Gdx.app.postRunnable(() -> super.game.changeScreen(super.game.CONNECTFOUR));
        //Thread lõpetab töö
        super.stop();
    }
}
