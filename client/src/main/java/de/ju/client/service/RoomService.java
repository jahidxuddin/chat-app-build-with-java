package de.ju.client.service;

import de.ju.client.models.Room;
import de.ju.client.networking.RoomClient;

import java.io.IOException;
import java.util.Collections;

public class RoomService {
    private final Object lock;
    private Room roomData;

    public RoomService() {
        this.lock = new Object();
    }

    public boolean joinRoom(String hostname, int port) {
        this.roomData = new Room(hostname, port, Collections.emptyList(), Collections.emptyList());

        RoomClient roomClient;
        try {
            roomClient = new RoomClient(this.lock, this.roomData);
        } catch (IOException e) {
            return false;
        }

        Thread roomClientThread = new Thread(roomClient);
        roomClientThread.start();

        return true;
    }

    public Room getRoomData() {
        return this.roomData;
    }

    public void setRoomData(Room roomData) {
        synchronized (lock) {
            this.roomData.setHostname(roomData.getHostname());
            this.roomData.setPort(roomData.getPort());
            this.roomData.setUser(roomData.getUser());
            this.roomData.setMessages(roomData.getMessages());
            lock.notify();
        }
    }
}
