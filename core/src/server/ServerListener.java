package server;

import com.connectfour.screens.gameScreens.ConnectFourScreen;

import java.io.ObjectInputStream;

public class ServerListener implements Runnable {

    public boolean running;
    private ObjectInputStream inputStream;
    private ConnectFourScreen gameScreen;

    public ServerListener(ObjectInputStream in, ConnectFourScreen gameScreen) {
        this.inputStream = in;
        this.gameScreen = gameScreen;
    }

    @Override
    public void run() {
        running = true;
    }

    public void stop() {
        running = false;
    }
}
