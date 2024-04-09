package com.hlstudios.orders.services.datasource.article;

import com.hlstudios.orders.entites.ShippingArticle;
import com.hlstudios.orders.repositories.ShippingArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class ShippingArticleServiceImpl implements ShippingArticleService {

    final ShippingArticleRepository shippingArticleRepository;

    public ShippingArticleServiceImpl(
            ShippingArticleRepository shippingArticleRepository
    ) {
        this.shippingArticleRepository = shippingArticleRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ShippingArticle> findAllListed() {
        return shippingArticleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<ShippingArticle> findAllPaged(Pageable pageable) {
        return shippingArticleRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ShippingArticle findById(UUID id) {
        return shippingArticleRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public ShippingArticle add(ShippingArticle entity) {
        return shippingArticleRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public ShippingArticle update(ShippingArticle entity) {
        return shippingArticleRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public ShippingArticle delete(ShippingArticle entity) {
        ShippingArticle shippingArticle = this.findById(entity.getId());
        shippingArticleRepository.delete(shippingArticle);
        return shippingArticle;
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public List<ShippingArticle> addAll(Collection<ShippingArticle> shippingArticles) {
        return shippingArticleRepository.saveAll(shippingArticles);
    }
}
