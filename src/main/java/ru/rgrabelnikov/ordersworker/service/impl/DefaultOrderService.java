package ru.rgrabelnikov.ordersworker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rgrabelnikov.ordersworker.domain.OrderEntity;
import ru.rgrabelnikov.ordersworker.domain.OrderStatus;
import ru.rgrabelnikov.ordersworker.exception.NotFoundException;
import ru.rgrabelnikov.ordersworker.repo.OrderRepo;
import ru.rgrabelnikov.ordersworker.service.OrderService;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepo repo;

    @Override
    @Transactional
    public OrderEntity updateStatus(final UUID id, final OrderStatus status) {
        final OrderEntity order = getById(id);
        order.setStatus(status);
        return repo.save(order);
    }

    @Override
    @Transactional
    public OrderEntity complete(final UUID id) {
        final OrderEntity order = getById(id);
        order.setStatus(OrderStatus.DONE);
        order.setProcessedAt(OffsetDateTime.now());
        return repo.save(order);
    }

    public OrderEntity getById(final UUID id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException(OrderEntity.class, id));
    }
}
