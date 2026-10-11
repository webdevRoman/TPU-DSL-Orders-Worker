package ru.rgrabelnikov.ordersworker.dto;

import java.util.UUID;

public record OrderCreatedEvent(
        UUID id
) {
}
