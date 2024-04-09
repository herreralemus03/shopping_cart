package com.hlstudios.orders.services.datasource.shipping;

import com.hlstudios.orders.entites.OrderShipping;
import com.hlstudios.orders.services.datasource.BaseDataSourceService;

import java.util.Collection;
import java.util.List;

public interface OrderShippingService extends BaseDataSourceService<OrderShipping> {

    List<OrderShipping> addAll(Collection<OrderShipping> orderShippingCollection);

}
