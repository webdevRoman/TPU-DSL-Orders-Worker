package ru.rgrabelnikov.ordersworker.configuration.properties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@JsonIgnoreProperties
@ConfigurationProperties(prefix = "event-handler")
public record EventHandlerProperties(
        @NotNull @Positive Long processingDelayMs
) {
}
