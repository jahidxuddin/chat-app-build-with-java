package de.ju.server.networking;

import de.ju.server.entities.Room;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RoomServer {
    private ServerSocket serverSocket;
    private static final int SERVER_PORT = 8080;
    private final List<Socket> sockets;

    public RoomServer() {
        try {
            this.serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
        this.sockets = new ArrayList<>();
    }

    public void start() {
        Socket socket;
        try {
            socket = this.serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sockets.add(socket);
    }

    public void sendRoomData(Socket client, Room room) throws IOException {
        new DataOutputStream(client.getOutputStream()).writeUTF(room.toString());
    }
}
