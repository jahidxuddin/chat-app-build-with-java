package de.ju.client.gui.components;

import de.ju.client.gui.listener.RoomInputTextFieldKeyListener;
import de.ju.client.gui.listener.RoomInputTextFieldMouseListener;
import de.ju.client.gui.utils.CustomJTextField;
import de.ju.client.service.RoomService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
        inputTextField.addMouseListener(new RoomInputTextFieldMouseListener(inputTextField));
        inputTextField.addKeyListener(new RoomInputTextFieldKeyListener(inputTextField, this.service));

        this.add(inputTextField, BorderLayout.CENTER);
    } 
}
