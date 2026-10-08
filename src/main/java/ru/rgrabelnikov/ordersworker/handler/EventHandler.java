package ru.rgrabelnikov.ordersworker.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventHandler {

    public void handle(final Message<String> message) {
        log.info("Got message with payload: {}", message.getPayload());
    }
}
