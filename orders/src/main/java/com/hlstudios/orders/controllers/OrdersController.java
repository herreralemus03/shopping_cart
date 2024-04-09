package com.hlstudios.orders.controllers;

import com.hlstudios.orders.dto.OrderDto;
import com.hlstudios.orders.services.web.order.OrderServiceWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrdersController {

    final OrderServiceWeb orderServiceWeb;

    public OrdersController(
            OrderServiceWeb orderServiceWeb
    ) {
        this.orderServiceWeb = orderServiceWeb;
    }

    @GetMapping(path = "/get/list")
    public ResponseEntity<?> getOrdersListed(){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", orderServiceWeb.listOrders());
            }});
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(path = "/get/paged")
    public ResponseEntity<?> getOrdersPaged(
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "25") final Integer size
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", orderServiceWeb.pageOrders(page, size));
            }});
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createOrder(
            @RequestBody OrderDto orderDto
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", orderServiceWeb.createOrder(orderDto));
            }});
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }
}
