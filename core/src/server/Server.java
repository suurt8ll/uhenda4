package server;

import com.badlogic.gdx.net.Socket;
import com.connectfour.Games;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server {

    public boolean running = false;
    protected Games game;
    protected Socket socket;
    protected ObjectInputStream inputStream;
    protected ObjectOutputStream outputStream;

    public Server(Games game) {
        this.game = game;
    }

    protected void initStreams() {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.flush();
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            //TODO errori puhul viib mängia tagasi main menusse
            e.printStackTrace();
        }
    }

    protected void sendPacket(TurnPacket packet) {
        try {
            outputStream.writeObject(packet);
            outputStream.flush();
        } catch (IOException e) {
            //TODO errorit võiks kuidagi handelida
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
        socket.dispose();
    }

}
