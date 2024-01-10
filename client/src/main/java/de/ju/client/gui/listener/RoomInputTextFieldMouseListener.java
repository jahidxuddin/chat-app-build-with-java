package de.ju.client.gui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class RoomInputTextFieldMouseListener implements MouseListener {
    private final JTextField inputTextField;

    public RoomInputTextFieldMouseListener(JTextField inputTextField) {
        this.inputTextField = inputTextField;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        inputTextField.setFocusable(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {}
}
