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

    public Host(Board board, Games game, int port) throws IOException {
        this.board = board;
        this.game = game;
        this.port = port;
        server = Gdx.net.newServerSocket(Net.Protocol.TCP, port, null);
    }

    @Override
    public void run() {

        //Loob ühendused
        Socket client1, client2;
        System.out.println("Wating for 1. player...");
        client1 = server.accept(null);
        System.out.printf("Player 1 connected from %s! \n", client1.getRemoteAddress());
        System.out.println("Wating for 2. player...");
        client2 = server.accept(null);
        System.out.printf("Player 2 connected from %s! \n", client2.getRemoteAddress());

        ObjectInputStream client1In, client2In;
        ObjectOutputStream client1Out, client2Out;
        Color client1Color, client2Color;

        System.out.println(waitAndReadInput(client1));

    }

    private Object waitAndReadInput(Socket client) {
        Object input = null;
        try {
            System.out.println("TT");
            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            inputStream.close();
            System.out.println("AA");
            while (input == null) {
                try {
                    input = inputStream.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    try {
                        this.wait(1000);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

}
