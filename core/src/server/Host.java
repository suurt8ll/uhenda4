package server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;
import com.connectfour.Board;
import com.connectfour.Games;
import java.io.IOException;

public class Host implements Runnable {

    private final Board board;
    private final Games game;
    private final int port;
    private final ServerSocket server;

    public Host(Board board, Games game, int port) throws IOException {
        this.board = board;
        this.game = game;
        this.port = port;
        server = Gdx.net.newServerSocket(Net.Protocol.TCP, port, null);
    }

    @Override
    public void run() {
        Socket client = server.accept(null);
        System.out.println("Client connected!");
    }
}
