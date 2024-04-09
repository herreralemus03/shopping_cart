package com.hlstudios.orders.controllers;

import com.hlstudios.orders.dto.ProductDto;
import com.hlstudios.orders.services.web.product.ProductServiceWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductsController {

    final ProductServiceWeb productServiceWeb;

    public ProductsController(
            ProductServiceWeb productServiceWeb
    ) {
        this.productServiceWeb = productServiceWeb;
    }

    @GetMapping("/get/paged")
    public ResponseEntity<?> getProductsPaged(
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "12") final Integer size
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.pageProducts(page, size));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/get/listed")
    public ResponseEntity<?> getProductsListed(){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.listProducts());
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProduct(
            @PathVariable("id") final String id
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.findProductById(UUID.fromString(id)));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestBody ProductDto productDto
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.addProduct(productDto));
            }});
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(
            @RequestBody ProductDto productDto
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.updateProduct(productDto));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable String id
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.deleteProduct(UUID.fromString(id)));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }
}
