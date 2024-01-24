package de.ju.server;

import de.ju.server.networking.RoomServer;

public class Main {
    public static void main(String[] args) {
        new RoomServer().run(8080);
    }
}
