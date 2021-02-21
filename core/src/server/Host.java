package server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;
import com.connectfour.Board;
import com.connectfour.Games;

import java.io.*;

public class Host implements Runnable {

    private final Board board;
    private final Games game;
    private final int port;
    private final ServerSocket server;
    public boolean running = false;

    public Host(Board board, Games game, int port) throws IOException {
        this.board = board;
        this.game = game;
        this.port = port;
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
        //Loob streamid
        try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            System.out.println("[SERVER] Streams with opponent have been made.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }

}
