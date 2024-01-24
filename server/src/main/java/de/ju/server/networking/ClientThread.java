package de.ju.server.networking;

import java.net.Socket;

public class ClientThread implements Runnable {
    private final Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        while (this.clientSocket.isConnected()) {
            
        }
    }
}
