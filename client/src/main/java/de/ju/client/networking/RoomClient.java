package de.ju.client.networking;

import de.ju.client.models.Room;

import java.io.IOException;

public class RoomClient implements Runnable {
    private final Object lock;
    private final Room roomData;
   // private final Socket socket;

    public RoomClient(Object lock, Room roomData) throws IOException {
        this.lock = lock;
        this.roomData = roomData;
        // this.socket = new Socket(roomData.hostname(), roomData.port());
    }

    private void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix-like operating systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (this.roomData == null) {
                    continue;
                }

                System.out.println(this.roomData);
            }
        }
    }
}
