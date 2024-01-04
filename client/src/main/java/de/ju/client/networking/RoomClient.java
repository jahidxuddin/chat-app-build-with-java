package de.ju.client.networking;

import java.io.IOException;
import java.net.Socket;

public class RoomClient {
    private Socket socket;

    public RoomClient(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
    }

    // Hier mit Server kommunizieren
    // Daten lesen und versenden
}
