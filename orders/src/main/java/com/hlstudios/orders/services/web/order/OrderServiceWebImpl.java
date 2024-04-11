package com.hlstudios.orders.services.web.order;

import com.hlstudios.orders.dto.OrderDto;
import com.hlstudios.orders.dto.ProductDto;
import com.hlstudios.orders.entites.Order;
import com.hlstudios.orders.entites.OrderShipping;
import com.hlstudios.orders.entites.ShippingArticle;
import com.hlstudios.orders.services.clients.ProductsServiceClient;
import com.hlstudios.orders.services.datasource.article.ShippingArticleService;
import com.hlstudios.orders.services.datasource.order.OrderService;
import com.hlstudios.orders.services.datasource.shipping.OrderShippingService;
import com.hlstudios.orders.utils.DtoParser;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceWebImpl implements OrderServiceWeb {

    final ProductsServiceClient productsServiceClient;
    final OrderService orderService;
    final OrderShippingService orderShippingService;
    final ShippingArticleService shippingArticleService;
    final ModelMapper modelMapper;
    final DtoParser dtoParser;

    public OrderServiceWebImpl(
            OrderService orderService,
            OrderShippingService orderShippingService,
            ShippingArticleService shippingArticleService,
            ModelMapper modelMapper,
            DtoParser dtoParser,
            ProductsServiceClient productsServiceClient
    ){
        this.orderService = orderService;
        this.orderShippingService = orderShippingService;
        this.shippingArticleService = shippingArticleService;
        this.modelMapper = modelMapper;
        this.dtoParser = dtoParser;
        this.productsServiceClient = productsServiceClient;
    }

    @Override
    public List<OrderDto> listOrders() {
        return orderService.findAllListed().stream()
                .map(e -> modelMapper.map(e, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderDto> pageOrders(int page, int size) {
        return orderService.findAllPaged(PageRequest.of(page, size))
                .map(e -> modelMapper.map(e, OrderDto.class));
    }

    @Override
    public OrderDto findOrderById(UUID id) {
        return modelMapper.map(orderService.findById(id), OrderDto.class);
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order =  modelMapper.map(orderDto, Order.class);
        Order orderResult = orderService.add(order);
        return modelMapper.map(orderResult, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order =  modelMapper.map(orderDto, Order.class);
        Order orderResult = orderService.add(order);
        return modelMapper.map(orderResult, OrderDto.class);
    }

    @Override
    public OrderDto deleteOrder(OrderDto orderDto) {
        Order order =  modelMapper.map(orderDto, Order.class);
        Order orderResult = orderService.delete(order);
        return modelMapper.map(orderResult, OrderDto.class);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        //orderDto.setCreationDate(Instant.now().toString());

        Order order = dtoParser.parseDtoToOrder(orderDto);
        Order result = orderService.add(order);

        List<OrderShipping> orderShippingList = order.getOrderShipping();
        orderShippingList.forEach(shipping -> shipping.setOrder(result));
        List<OrderShipping> orderShippingListResult = orderShippingService.addAll(orderShippingList);

        orderShippingListResult.forEach(shipping -> {
            List<ShippingArticle> articles = shipping.getArticles();
            articles.forEach(article -> {
                article.setShipping(shipping);
            });
        });

        List<ShippingArticle> articles = orderShippingListResult.stream()
                .map(OrderShipping::getArticles)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<ShippingArticle> articlesResult = shippingArticleService.addAll(articles);

        orderShippingListResult.forEach(shipping -> {
            List<ShippingArticle> shippingArticlesCopy = articlesResult.stream()
                                .takeWhile(e -> e.getShipping().getId().equals(shipping.getId()))
                                .collect(Collectors.toList());
            shipping.setArticles(shippingArticlesCopy);
        });

        result.setOrderShipping(orderShippingListResult);

        return dtoParser.parseOrderToDto(result);
    }
}
