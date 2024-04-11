package com.hlstudios.orders.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hlstudios.orders.dto.*;
import com.hlstudios.orders.entites.*;
import com.hlstudios.orders.services.clients.ProductsServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DtoParser {

    final ModelMapper modelMapper;
    final ProductsServiceClient productsServiceClient;

    public DtoParser(
            ModelMapper modelMapper,
            ProductsServiceClient productsServiceClient
    ) {
        this.modelMapper = modelMapper;
        this.productsServiceClient = productsServiceClient;
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
                //.creationDate(Instant.now().toString())
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
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productDto = null;

        try {
            productDto = objectMapper.readValue(article.getProductData(), ProductDto.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }

        return ShippingArticleDto.builder()
                .id(article.getId())
                .amount(article.getAmount())
                .productData(productDto)
                .articleNotes(article.getArticleNotes())
                .apiId(productDto.getApiId())
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
        //order.setCreationDate(Date.from(Instant.now()));
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
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        ShippingArticle article = new ShippingArticle();

        ResponseEntity<?> response = productsServiceClient.getProductById(articleDto.getApiId());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        ProductDto productDto = modelMapper.map(body.getOrDefault("body", null), ProductDto.class);

        try {
            article.setProductData(objectMapper.writeValueAsString(productDto));
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        article.setId(articleDto.getId());
        article.setArticleNotes(articleDto.getArticleNotes());
        article.setAmount(articleDto.getAmount());

        return article;
    }
}
