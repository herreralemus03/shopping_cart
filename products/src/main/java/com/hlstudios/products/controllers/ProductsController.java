package com.hlstudios.products.controllers;

import com.hlstudios.products.dto.ProductDto;
import com.hlstudios.products.services.web.ProductsServiceWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    private final ProductsServiceWeb productsServiceWeb;

    public ProductsController(ProductsServiceWeb productsServiceWeb) {
        this.productsServiceWeb = productsServiceWeb;
    }

    @GetMapping(path = {"/", ""})
    public ResponseEntity<?> getAllProducts(
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(name = "sort", required=false, defaultValue = "asc") String sort,
            @RequestParam(name = "category", required = false, defaultValue = "") String category
    ){
        try {
            List<ProductDto> productDtos = productsServiceWeb.getAllProducts(limit, sort, category);
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("message", "ok");
                put("body", productDtos);
            }});
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getProductById(
            @PathVariable(name = "id") Integer id
    ) {
        try {
            ProductDto productDto = productsServiceWeb.getProductById(id);
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("message", "ok");
                put("body", productDto);
            }});
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<?> getProductCategories(){
        try {
            List<String> categories = productsServiceWeb.getProductCategories();

            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("message", "ok");
                put("body", categories);
            }});

        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }
}
