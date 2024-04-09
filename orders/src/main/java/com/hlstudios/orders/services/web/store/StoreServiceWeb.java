package com.hlstudios.orders.services.web.store;

import com.hlstudios.orders.dto.StoreDto;
import org.springframework.data.domain.Page;

public interface StoreServiceWeb {

    Page<StoreDto> pageStores(Integer page, Integer size);
}
