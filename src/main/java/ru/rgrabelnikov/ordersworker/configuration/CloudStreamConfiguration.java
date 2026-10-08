package ru.rgrabelnikov.ordersworker.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.rgrabelnikov.ordersworker.handler.EventHandler;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class CloudStreamConfiguration {

    private final EventHandler handler;

    @Bean
    public Consumer<Message<String>> orderCreatedConsumer() {
        return handler::handle;
    }
}
