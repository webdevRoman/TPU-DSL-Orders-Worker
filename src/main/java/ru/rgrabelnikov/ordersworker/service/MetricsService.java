package ru.rgrabelnikov.ordersworker.service;

public interface MetricsService {

    void recordOrderProcessingTimer(Runnable action);
}
