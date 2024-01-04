package de.ju.client.gui.components;

import de.ju.client.gui.utils.CustomJTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoomInput extends JPanel {
    public RoomInput() {
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

        this.add(inputTextField, BorderLayout.CENTER);
    }
}
