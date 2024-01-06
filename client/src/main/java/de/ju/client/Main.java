package de.ju.client;

import de.ju.client.gui.RoomWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RoomWindow::new);
    }
}
