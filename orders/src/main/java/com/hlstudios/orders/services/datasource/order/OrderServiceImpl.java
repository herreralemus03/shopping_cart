package com.hlstudios.orders.services.datasource.order;

import com.hlstudios.orders.entites.Order;
import com.hlstudios.orders.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Order> findAllListed() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<Order> findAllPaged(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Order findById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Order delete(Order order) {
        Order current = this.findById(order.getId());
        orderRepository.delete(current);
        return current;
    }
}
