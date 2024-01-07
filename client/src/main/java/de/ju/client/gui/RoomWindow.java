package de.ju.client.gui;

import de.ju.client.gui.components.RoomMessages;
import de.ju.client.gui.components.RoomInput;
import de.ju.client.gui.components.RoomTitle;
import de.ju.client.service.RoomService;

import javax.swing.*;
import java.awt.*;

public class RoomWindow extends JFrame {
    private final RoomService service;

    public RoomWindow() {
        this.service = new RoomService();

        boolean isJoined = this.service.joinRoom("localhost", 1337);
        if (!isJoined) {
            System.out.println("\nSomething went wrong.");
            System.exit(0);
        }

        setupWindow();
    }

    private void setupWindow() {
        this.setTitle(service.getRoomData().getHostname() + " - Chat App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(375, 750));
        this.setMinimumSize(new Dimension(getWidth(), getHeight()));
        this.setupContentPane();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setupContentPane() {
        this.getContentPane().setBackground(new Color(16, 20, 28));
        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add(new RoomTitle(), BorderLayout.NORTH);
        this.getContentPane().add(new RoomMessages(service.getRoomData().getMessages()), BorderLayout.CENTER);
        this.getContentPane().add(new RoomInput(this.service), BorderLayout.SOUTH);
    }
}
