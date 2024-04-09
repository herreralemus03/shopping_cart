package com.hlstudios.orders.services.datasource.shipping;

import com.hlstudios.orders.entites.OrderShipping;
import com.hlstudios.orders.repositories.OrderShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class OrderShippingServiceImpl implements OrderShippingService {

    final OrderShippingRepository orderShippingRepository;

    public OrderShippingServiceImpl(
            OrderShippingRepository orderShippingRepository
    ) {
        this.orderShippingRepository = orderShippingRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<OrderShipping> findAllListed() {
        return orderShippingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<OrderShipping> findAllPaged(Pageable pageable) {
        return orderShippingRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public OrderShipping findById(UUID id) {
        return orderShippingRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public OrderShipping add(OrderShipping entity) {
        return orderShippingRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public OrderShipping update(OrderShipping entity) {
        return orderShippingRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public OrderShipping delete(OrderShipping entity) {
        OrderShipping current = this.findById(entity.getId());
        orderShippingRepository.delete(current);
        return current;
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public List<OrderShipping> addAll(Collection<OrderShipping> orderShippingCollection) {
        return orderShippingRepository.saveAll(orderShippingCollection);
    }
}
