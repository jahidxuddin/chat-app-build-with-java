package de.ju.client.gui.components;

import de.ju.client.gui.utils.CustomJTextField;
import de.ju.client.models.Message;
import de.ju.client.models.Room;
import de.ju.client.service.RoomService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomInput extends JPanel {
    private final RoomService service;

    public RoomInput(RoomService service) {
        this.service = service;
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(0, 75));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout());
        this.setupInputField();
    }

    private void setupInputField() {
        JTextField inputTextField = new CustomJTextField(100, "Send a message", Color.WHITE);
        inputTextField.setBackground(new Color(24, 26, 31, 255));
        inputTextField.setFont(new Font(inputTextField.getFont().getName(), Font.PLAIN, 16));
        inputTextField.setBorder(new EmptyBorder(0, 12, 0, 12));
        inputTextField.setFocusable(false);
        inputTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                inputTextField.setFocusable(true);
            }
        });
        inputTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !inputTextField.getText().isEmpty()) {
                    Room roomData = service.getRoomData();

                    List<Message> messages = new ArrayList<>(roomData.getMessages());
                    messages.add(new Message(inputTextField.getText(), "ju_dev", LocalDateTime.now()));

                    service.setRoomData(new Room(roomData.getId(), roomData.getHostname(), roomData.getPort(), roomData.getUser(), messages));

                    inputTextField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.add(inputTextField, BorderLayout.CENTER);
    }
}
