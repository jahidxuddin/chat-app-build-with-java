package de.ju.client;

import de.ju.client.gui.RoomWindow;
import de.ju.client.service.RoomService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // TODO: Dieser Service braucht Daten von Client Klasse
        RoomService roomService = new RoomService();
        SwingUtilities.invokeLater(() -> new RoomWindow(roomService));
    }
}
