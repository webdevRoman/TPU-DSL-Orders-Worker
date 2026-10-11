package ru.rgrabelnikov.ordersworker.service.impl;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rgrabelnikov.ordersworker.service.MetricsService;

@Service
@RequiredArgsConstructor
public class DefaultMetricsService implements MetricsService {

    private final MeterRegistry meterRegistry;

    @Override
    public void recordOrderProcessingTimer(final Runnable action) {
        Timer.builder("shop.order.processing.duration.seconds")
                .description("Сколько секунд прошло от получения сообщения из RabbitMQ до перевода заказа в DONE")
                .publishPercentiles(0.5, 0.95, 0.99)
                .publishPercentileHistogram()
                .register(meterRegistry)
                .record(action);
    }
}
