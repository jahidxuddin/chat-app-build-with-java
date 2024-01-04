package de.ju.client.models;

import java.util.List;

public record Room(
        String hostname,
        int port,
        List<User> user,
        List<Message> messages
) { }