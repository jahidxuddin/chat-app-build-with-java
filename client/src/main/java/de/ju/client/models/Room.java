package de.ju.client.models;

import java.util.List;
import java.util.Objects;

public class Room {
    private String hostname;
    private int port;
    private List<User> user;
    private List<Message> messages;

    public Room(String hostname, int port, List<User> user, List<Message> messages) {
        this.hostname = hostname;
        this.port = port;
        this.user = user;
        this.messages = messages;
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

    public synchronized List<User> getUser() {
        return user;
    }

    public synchronized void setUser(List<User> user) {
        this.user = user;
    }

    public synchronized List<Message> getMessages() {
        return messages;
    }

    public synchronized void setMessages(List<Message> messages) {
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