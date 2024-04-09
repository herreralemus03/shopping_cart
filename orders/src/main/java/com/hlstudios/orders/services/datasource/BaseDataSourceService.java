package com.hlstudios.orders.services.datasource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BaseDataSourceService<T> {

    List<T> findAllListed();

    Page<T> findAllPaged(Pageable pageable);

    T findById(UUID id);

    T add(T entity);

    T update(T entity);

    T delete(T entity);

}
