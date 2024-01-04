package de.ju.client.gui.components;

import de.ju.client.models.Message;
import de.ju.client.service.RoomService;

import javax.swing.*;
import java.util.List;

public class RoomMessages extends JPanel {
    private final List<Message> messages;

    public RoomMessages(RoomService service) {
        this.messages = service.readRoomData().messages();

        this.setOpaque(false);
    }
}
