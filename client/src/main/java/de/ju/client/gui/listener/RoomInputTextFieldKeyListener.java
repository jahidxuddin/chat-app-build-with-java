package de.ju.client.gui.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;

import javax.swing.JTextField;

import de.ju.client.models.Message;
import de.ju.client.models.Room;
import de.ju.client.service.RoomService;

public class RoomInputTextFieldKeyListener implements KeyListener {
    private final JTextField inputTextField;
    private final RoomService service;

    public RoomInputTextFieldKeyListener(JTextField inputTextField, RoomService service) {
        this.inputTextField = inputTextField;
        this.service = service;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !inputTextField.getText().isEmpty()) {
            Room roomData = service.getRoomData();
            roomData.getMessages().add(new Message(inputTextField.getText(), "ju_dev", LocalDateTime.now()));
            
            service.setRoomData(roomData);
            
            this.inputTextField.setText("");
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
}
