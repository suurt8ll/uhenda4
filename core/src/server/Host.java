package server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;
import com.connectfour.Board;
import com.connectfour.Games;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Host implements Runnable {

    private final Board board;
    private final Games game;
    private final ServerSocket server;
    public boolean running = false;

    public Host(Games game, int port) throws IOException {
        this.board = game.CONNECTFOUR.board;
        this.game = game;
        server = Gdx.net.newServerSocket(Net.Protocol.TCP, port, null);
    }

    @Override
    public void run() {
        running = true;
        //Loob ühendused
        Socket client;
        System.out.println("[SERVER] Wating for opponent...");
        client = server.accept(null);
        System.out.printf("[SERVER] Opponent connected from %s! \n", client.getRemoteAddress());
        //Teeb coinflipi, et selgitada, kes alustab. 1 = host, 2 = vastane
        boolean opponentsTurn;
        Random random = new Random();
        opponentsTurn = random.nextBoolean();
        TurnPacket firstTurn = new TurnPacket(1, opponentsTurn, 0);
        //Loob streamid
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("[SERVER] Streams with opponent have been made.");
            out.writeObject(firstTurn);
            out.flush();
            if (opponentsTurn) {
                game.CONNECTFOUR.whoseTurn = 2;
            } else {
                game.CONNECTFOUR.whoseTurn = 1;
            }
            Gdx.app.postRunnable(() -> game.changeScreen(game.CONNECTFOUR));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

}
