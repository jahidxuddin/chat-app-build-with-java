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
        try {
            DataInputStream inputStream = new DataInputStream(this.socket.getInputStream());

            while (this.socket.isConnected()) {
                String input = inputStream.readUTF();
                if (!isValidRoomDataFormat(input)) {
                    continue;
                }

                Optional<Room> newRoomDataOptional = jsonToRoomData(input);

                if (newRoomDataOptional.isEmpty()) {
                    continue;
                }

                Room newRoomData = newRoomDataOptional.get();

                synchronized (this.lock) {
                    this.roomData.setHostname(newRoomData.getHostname());
                    this.roomData.setPort(newRoomData.getPort());
                    this.roomData.setUser(newRoomData.getUser());
                    this.roomData.setMessages(newRoomData.getMessages());
                    this.lock.notify();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
