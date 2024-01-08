package de.ju.client.networking;

import de.ju.client.models.Room;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RoomClient implements Runnable {
    private final Object lock;
    private final Room roomData;
    private final Socket socket;

    public RoomClient(Object lock, Room roomData) throws IOException {
        this.lock = lock;
        this.roomData = roomData;
        this.socket = new Socket(roomData.getHostname(), roomData.getPort());
    }

    @Override
    public void run() {
        RoomDataReceiver dataReceiver = new RoomDataReceiver(this.lock, this.roomData, this.socket);
        Thread dataReceiverThread = new Thread(dataReceiver);
        dataReceiverThread.start();

        DataOutputStream outputStream;
        try {
            outputStream = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Room prevRoomData;
        while (this.socket.isConnected()) {
            prevRoomData = this.roomData;
            synchronized (this.lock) {
                try {
                    this.lock.wait();
                } catch (InterruptedException ignored) {
                }

                if (!this.roomData.compareValues(prevRoomData)) {
                    continue;
                }

                try {
                    outputStream.writeUTF(roomData.toString());
                } catch (IOException ignored) {
                }
            }
        }
    }
}
