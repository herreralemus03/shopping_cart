package com.hlstudios.orders.services.clients;

import com.hlstudios.orders.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "products-service"
)
public interface ProductsServiceClient {

    @GetMapping("/products/api/v1/products/{id}")
    ResponseEntity<?> getProductById(@PathVariable(name = "id") Long id);

}
