package com.hlstudios.orders.repositories;

import com.hlstudios.orders.entites.OrderShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderShippingRepository extends JpaRepository<OrderShipping, UUID> {

}
