package com.hlstudios.orders.utils;

import com.hlstudios.orders.dto.*;
import com.hlstudios.orders.entites.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoParser {

    final ModelMapper modelMapper;

    public DtoParser(
            ModelMapper modelMapper
    ) {
        this.modelMapper = modelMapper;
    }

    public OrderDto parseOrderToDto(Order order){
        List<OrderShippingDto> orderShippingDtos = order.getOrderShipping()
                .stream()
                .map(this::parseShippingToDto)
                .collect(Collectors.toList());

        return OrderDto.builder()
                .id(order.getId())
                .country(modelMapper.map(order.getCountry(), CountryDto.class))
                .store(modelMapper.map(order.getStore(), StoreDto.class))
                .shipping(orderShippingDtos)
                .creationDate(Instant.now().toString())
                .build();
    }

    public OrderShippingDto parseShippingToDto(OrderShipping shipping){
        List<ShippingArticleDto> shippingArticleDtoList = shipping.getArticles()
                    .stream()
                    .map(this::parseArticleToDto)
                    .collect(Collectors.toList());

        return OrderShippingDto.builder()
                .id(shipping.getId())
                .notes(shipping.getNotes())
                .client(modelMapper.map(shipping.getClient(), ClientDto.class))
                .articles(shippingArticleDtoList)
                .build();
    }

    public ShippingArticleDto parseArticleToDto(ShippingArticle article){
        return ShippingArticleDto.builder()
                .id(article.getId())
                .amount(article.getAmount())
                .product(modelMapper.map(article.getProduct(), ProductDto.class))
                .articleNotes(article.getArticleNotes())
                .build();
    }

    public Order parseDtoToOrder(OrderDto orderDto){
        List<OrderShipping> shipping = orderDto.getShipping()
                .stream()
                .map(this::parseDtoToShipping)
                .collect(Collectors.toList());

        Order order = new Order();
        order.setId(orderDto.getId());
        order.setStore(modelMapper.map(orderDto.getStore(), Store.class));
        order.setCountry(modelMapper.map(orderDto.getCountry(), Country.class));
        order.setOrderShipping(shipping);
        order.setCreationDate(Date.from(Instant.now()));
        return order;
    }

    public OrderShipping parseDtoToShipping(
            OrderShippingDto orderShippingDto
    ){
        List<ShippingArticle> articles = orderShippingDto.getArticles()
                .stream()
                .map(this::parseDtoToArticle)
                .collect(Collectors.toList());

        OrderShipping orderShipping = new OrderShipping();

        orderShipping.setId(orderShippingDto.getId());
        orderShipping.setNotes(orderShippingDto.getNotes());
        orderShipping.setClient(modelMapper.map(orderShippingDto.getClient(), Client.class));
        orderShipping.setArticles(articles);

        return orderShipping;
    }

    public ShippingArticle parseDtoToArticle(
            ShippingArticleDto articleDto
    ){
        ShippingArticle article = new ShippingArticle();

        article.setId(articleDto.getId());
        article.setArticleNotes(articleDto.getArticleNotes());
        article.setProduct(modelMapper.map(articleDto.getProduct(), Product.class));
        article.setAmount(articleDto.getAmount());

        return article;
    }
}
