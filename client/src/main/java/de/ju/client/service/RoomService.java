package de.ju.client.service;

import de.ju.client.models.Room;
import de.ju.client.networking.RoomClient;

import java.io.IOException;
import java.util.Collections;

public class RoomService {
    private volatile Room roomData;

    public boolean joinRoom(String hostname, int port) {
        this.roomData = new Room(hostname, port, Collections.emptyList(), Collections.emptyList());

        RoomClient roomClient;
        try {
            roomClient = new RoomClient(this.roomData);
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
        this.roomData = roomData;
    }
}
