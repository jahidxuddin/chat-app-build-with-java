package de.ju.server.networking;

import java.io.IOException;
import java.net.ServerSocket;

import de.ju.server.service.RoomManagerService;
import de.ju.server.utils.Logger;

public class RoomServer {
    private ServerSocket serverSocket;
    private RoomManagerService roomManagerService;
    private ClientConnectionHandler clientConnectionHandler;

    public void run(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            Logger.error("Failed to run server on " + port);
            System.exit(500);
        }

        Logger.info("Initializing 'RoomManagerService'");
        this.roomManagerService = new RoomManagerService();

        Logger.info("Initializing 'ClientConnectionHandler'");
        this.clientConnectionHandler = new ClientConnectionHandler(this.serverSocket, this.roomManagerService);
        new Thread(this.clientConnectionHandler).start();

        Logger.info("Server is listening on port: " + port);
    }
}
