package de.ju.server.entities;

import de.ju.server.models.MessageDTO;
import de.ju.server.models.UserDTO;

import java.util.List;
import java.util.UUID;

public class Room {
    private UUID id;
    private String hostname;
    private int port;
    private List<UserDTO> user;
    private List<MessageDTO> messages;

    public Room(UUID id, String hostname, int port, List<UserDTO> user, List<MessageDTO> messages) {
        this.id = id;
        this.hostname = hostname;
        this.port = port;
        this.user = user;
        this.messages = messages;
    }

    public synchronized UUID getId() {
        return id;
    }

    public synchronized void setId(UUID id) {
        this.id = id;
    }

    public synchronized String getHostname() {
        return hostname;
    }

    public synchronized void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public synchronized int getPort() {
        return port;
    }

    public synchronized void setPort(int port) {
        this.port = port;
    }

    public synchronized List<UserDTO> getUser() {
        return user;
    }

    public synchronized void setUser(List<UserDTO> user) {
        this.user = user;
    }

    public synchronized List<MessageDTO> getMessages() {
        return messages;
    }

    public synchronized void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }

    public boolean compareValues(Room room) {
        return this.hostname.equals(room.hostname) &&
                this.port == room.port &&
                this.user.equals(room.user) &&
                this.messages.equals(room.messages);
    }

    @Override
    public String toString() {
        return "Room[" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                ", user=" + user +
                ", messages=" + messages +
                ']';
    }
}