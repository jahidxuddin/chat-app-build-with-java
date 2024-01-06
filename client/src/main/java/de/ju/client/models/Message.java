package de.ju.client.models;

import java.time.LocalDateTime;

public record Message(
        String content,
        String author,
        LocalDateTime createdAt
) { }