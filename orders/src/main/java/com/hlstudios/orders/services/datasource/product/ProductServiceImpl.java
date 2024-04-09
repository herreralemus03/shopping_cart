package com.hlstudios.orders.services.datasource.product;

import com.hlstudios.orders.entites.Product;
import com.hlstudios.orders.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    public ProductServiceImpl(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Product> findAllListed() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<Product> findAllPaged(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Product findById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Product delete(Product product) {
        Product current = this.findById(product.getId());
        productRepository.delete(current);
        return current;
    }
}
