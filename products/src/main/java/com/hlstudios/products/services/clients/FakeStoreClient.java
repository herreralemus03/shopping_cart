package com.hlstudios.products.services.clients;


import com.hlstudios.products.models.ProductFakeStore;
import feign.Request;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreClient {

    final String URI = "https://fakestoreapi.com/products";

    final RestTemplate restTemplate;

    public FakeStoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductFakeStore> getAllProducts(Integer limit, String sort) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URI)
                .queryParam("limit", limit)
                .queryParam("sort", sort)
                .build();

        ProductFakeStore[] products = restTemplate.exchange(
                uriComponents.toUriString(),
                HttpMethod.GET,
                null,
                ProductFakeStore[].class
        ).getBody();

        if(products != null) {
            return List.of(products);
        }
        return List.of();
    }

    public ProductFakeStore getProductById(Integer id) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URI.concat("/").concat(id.toString()))
                .build();

        ProductFakeStore product = restTemplate.exchange(
                uriComponents.toUriString(),
                HttpMethod.GET,
                null,
                ProductFakeStore.class
        ).getBody();

        return product;
    }

    public List<ProductFakeStore> getAllProductsByCategory(Integer limit, String sort, String category) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URI.concat("/category/").concat(category))
                .queryParam("limit", limit)
                .queryParam("sort", sort)
                .build();

        ProductFakeStore[] products = restTemplate.exchange(
                uriComponents.toUriString(),
                HttpMethod.GET,
                null,
                ProductFakeStore[].class
        ).getBody();

        if(products != null) {
            return List.of(products);
        }
        return List.of();
    }

    public List<String> getProductCategories() {
        return Arrays.asList(restTemplate.exchange(URI, HttpMethod.GET, null, String[].class).getBody());
    }
}
