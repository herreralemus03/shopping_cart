package com.hlstudios.products.services.web;

import com.hlstudios.products.dto.ProductDto;
import com.hlstudios.products.services.clients.FakeStoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceWeb {

    private final FakeStoreClient fakeStoreClient;


    public ProductsServiceWeb(
            FakeStoreClient fakeStoreClient
    ) {
        this.fakeStoreClient = fakeStoreClient;
    }

    public List<ProductDto> getAllProducts(Integer limit, String sort, String category){
        if(!category.trim().isEmpty()){
            return fakeStoreClient.getAllProductsByCategory(limit, sort, category)
                    .stream()
                    .map(ProductDto::new)
                    .collect(Collectors.toList());
        }
        return fakeStoreClient.getAllProducts(limit, sort)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public List<String> getProductCategories(){
        return fakeStoreClient.getProductCategories();
    }
}
