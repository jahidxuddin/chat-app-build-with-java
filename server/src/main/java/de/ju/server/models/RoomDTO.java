package de.ju.server.models;

import java.util.List;
import java.util.UUID;

public record RoomDTO(
        UUID id,
        List<UserDTO> users,
        List<MessageDTO> messages
) { }
