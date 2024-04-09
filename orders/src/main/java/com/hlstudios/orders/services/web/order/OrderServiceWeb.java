package com.hlstudios.orders.services.web.order;

import com.hlstudios.orders.dto.OrderDto;
import com.hlstudios.orders.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface OrderServiceWeb {

    public List<OrderDto> listOrders();

    public Page<OrderDto> pageOrders(int page, int size);

    public OrderDto findOrderById(UUID id);

    public OrderDto addOrder(OrderDto order);

    public OrderDto updateOrder(OrderDto order);

    public OrderDto deleteOrder(OrderDto order);

    public OrderDto createOrder(OrderDto orderDto);
}
