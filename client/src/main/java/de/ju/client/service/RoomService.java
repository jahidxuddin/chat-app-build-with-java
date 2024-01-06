package de.ju.client.service;

import de.ju.client.models.Room;
import de.ju.client.networking.RoomClient;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RoomService {
    private final BlockingQueue<Room> roomBlockingQueue;

    public RoomService() {
        this.roomBlockingQueue = new ArrayBlockingQueue<>(1);
    }

    public boolean joinRoom(String hostname, int port) {
        RoomClient roomClient;
        try {
            roomClient = new RoomClient(hostname, port, this.roomBlockingQueue);
        } catch (IOException e) {
            return false;
        }

        Thread roomClientThread = new Thread(roomClient);
        roomClientThread.start();

        return true;
    }

    public Optional<Room> getRoomData() {
        try {
            return Optional.of(this.roomBlockingQueue.take());
        } catch (InterruptedException e) {
            return Optional.empty();
        }
    }

    public void updateRoomData(Room roomData) throws InterruptedException {
        this.roomBlockingQueue.put(roomData);
    }
}
