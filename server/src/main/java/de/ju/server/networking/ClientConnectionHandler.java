package de.ju.server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import de.ju.server.service.RoomManagerService;
import de.ju.server.utils.Logger;

public class ClientConnectionHandler implements Runnable {
    private final ServerSocket serverSocket;
    private final RoomManagerService service;
    private final List<Thread> clientSockets;

    public ClientConnectionHandler(ServerSocket serverSocket, RoomManagerService roomManagerService) {
        this.serverSocket = serverSocket;
        this.service = roomManagerService;
        this.clientSockets = new ArrayList<>();
    }

    private Thread createClientThread(Socket clienSocket) {
        return new Thread(new ClientThread(clienSocket));
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket clientSocket = this.serverSocket.accept();
                this.clientSockets.add(createClientThread(clientSocket));
                Logger.info(clientSocket.getInetAddress().getHostName() + " connected");
            }
        } catch (IOException e) {
            Logger.error("Connection error");
            System.exit(503);
        }
    }
}
