package de.ju.client.networking;

import de.ju.client.models.Room;

import java.io.IOException;

public class RoomClient implements Runnable {
    private final Room roomData;
   // private final Socket socket;

    public RoomClient(Room roomData) throws IOException {
        this.roomData = roomData;
        // this.socket = new Socket(roomData.hostname(), roomData.port());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (roomData == null) {
                System.out.println("RoomData is null");
                continue;
            }

            System.out.println(roomData);
            roomData.messages().forEach(System.out::println);
        }
    }
}
