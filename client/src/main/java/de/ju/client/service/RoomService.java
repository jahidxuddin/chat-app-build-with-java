package de.ju.client.service;

import de.ju.client.models.Room;
import de.ju.client.networking.RoomClient;

import java.util.Collections;
import java.util.Optional;

public class RoomService {
    private RoomClient roomClient;

    public RoomService() {

    }

    public Optional<Room> joinRoom(String hostname, int port, String username) {
        return Optional.empty();
    }

    public Room readRoomData() {
        return new Room("localhost", 1337, Collections.emptyList(), Collections.emptyList());
    }
}
