package ru.rgrabelnikov.ordersworker.service;

import ru.rgrabelnikov.ordersworker.domain.OrderEntity;
import ru.rgrabelnikov.ordersworker.domain.OrderStatus;

import java.util.UUID;

public interface OrderService {

    OrderEntity updateStatus(UUID id, OrderStatus status);

    OrderEntity complete(UUID id);
}
