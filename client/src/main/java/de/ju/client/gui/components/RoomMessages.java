package de.ju.client.gui.components;

import de.ju.client.models.Message;

import javax.swing.*;
import java.util.List;

public class RoomMessages extends JPanel {
    private final List<Message> messages;

    public RoomMessages(List<Message> messages) {
        this.messages = messages;

        this.setOpaque(false);
    }
}
