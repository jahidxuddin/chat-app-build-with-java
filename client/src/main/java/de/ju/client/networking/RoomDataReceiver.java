package de.ju.client.networking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ju.client.models.Room;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomDataReceiver implements Runnable {
    private final ObjectMapper objectMapper;
    private final Object lock;
    private final Room roomData;
    private final Socket socket;

    public RoomDataReceiver(Object lock, Room roomData, Socket socket) {
        this.objectMapper = new ObjectMapper();
        this.lock = lock;
        this.roomData = roomData;
        this.socket = socket;
    }

    @Override
    public void run() {
        try(DataInputStream inputStream = new DataInputStream(this.socket.getInputStream())) {
            while (this.socket.isConnected()) {
                String input = inputStream.readUTF();
                handleInput(input);
            }
        } catch (IOException ignored) {
        }
    }

    private boolean handleInput(String input) {
        if (!isValidRoomDataFormat(input)) return false;

        Optional<Room> newRoomDataOptional = jsonToRoomData(input);
        if (newRoomDataOptional.isEmpty()) {
            return false;
        }

        updateRoomData(newRoomDataOptional.get());
        
        return true;
    }

    private void updateRoomData(Room newRoomData) {
        synchronized (this.lock) {
            this.roomData.setId(newRoomData.getId());
            this.roomData.setHostname(newRoomData.getHostname());
            this.roomData.setPort(newRoomData.getPort());
            this.roomData.setUser(newRoomData.getUser());
            this.roomData.setMessages(newRoomData.getMessages());
            this.lock.notify();
        }
    }

    private boolean isValidRoomDataFormat(String jsonString) {
        String ROOM_PATTERN = "\\{\\s*\"hostname\":\\s*\"(.*?)\",\\s*\"port\":\\s*(\\d+),\\s*\"user\":\\s*\\[(.*?)],\\s*\"messages\":\\s*\\[(.*?)]\\s*}";
        Pattern pattern = Pattern.compile(ROOM_PATTERN);
        Matcher matcher = pattern.matcher(jsonString);
        return matcher.matches();
    }

    private Optional<Room> jsonToRoomData(String jsonString) {
        try {
            return Optional.ofNullable(objectMapper.readValue(jsonString, Room.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
