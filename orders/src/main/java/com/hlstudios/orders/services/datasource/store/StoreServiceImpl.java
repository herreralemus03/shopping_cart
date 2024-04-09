package com.hlstudios.orders.services.datasource.store;

import com.hlstudios.orders.entites.Store;
import com.hlstudios.orders.repositories.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StoreServiceImpl implements StoreService {
    final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> findAllListed() {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<Store> findAllPaged(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    @Override
    public Store findById(UUID id) {
        return null;
    }

    @Override
    public Store add(Store entity) {
        return null;
    }

    @Override
    public Store update(Store entity) {
        return null;
    }

    @Override
    public Store delete(Store entity) {
        return null;
    }
}
