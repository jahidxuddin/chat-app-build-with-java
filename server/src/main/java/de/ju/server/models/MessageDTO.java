package de.ju.server.models;

import java.time.LocalDateTime;

public record MessageDTO (
    String content,
    String author,
    LocalDateTime createdAt
) { }