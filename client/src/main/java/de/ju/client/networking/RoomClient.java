package de.ju.client.networking;

import de.ju.client.models.Room;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;

public class RoomClient implements Runnable {
   // private final Socket socket;
    private final BlockingQueue<Room> roomBlockingQueue;

    public RoomClient(String hostname, int port, BlockingQueue<Room> roomBlockingQueue) throws IOException {
        // this.socket = new Socket(hostname, port);
        this.roomBlockingQueue = roomBlockingQueue;
    }

    @Override
    public void run() {
        try {
            roomBlockingQueue.put(new Room("localhost", 1337, Collections.emptyList(), Collections.emptyList()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
