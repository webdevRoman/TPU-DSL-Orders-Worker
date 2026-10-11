package ru.rgrabelnikov.ordersworker.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ru.rgrabelnikov.ordersworker.configuration.properties.EventHandlerProperties;
import ru.rgrabelnikov.ordersworker.domain.OrderStatus;
import ru.rgrabelnikov.ordersworker.dto.OrderCreatedEvent;
import ru.rgrabelnikov.ordersworker.service.OrderService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventHandler {

    private final EventHandlerProperties props;

    private final OrderService service;

    public void handle(final Message<OrderCreatedEvent> message) {
        log.info("Got message with payload: {}", message.getPayload());
        final UUID orderId = message.getPayload().id();
        if (orderId == null) {
            log.error("Invalid event payload");
            return;
        }

        log.info("Start processing order {}", orderId);
        service.updateStatus(orderId, OrderStatus.PROCESSING);

        log.info("Delay processing for {} ms", props.processingDelayMs());
        try {
            Thread.sleep(props.processingDelayMs());
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            return;
        }

        service.complete(orderId);
        log.info("Completed order {}", orderId);
    }
}
