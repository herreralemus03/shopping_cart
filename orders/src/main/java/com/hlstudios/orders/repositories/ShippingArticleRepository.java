package com.hlstudios.orders.repositories;

import com.hlstudios.orders.entites.ShippingArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ShippingArticleRepository extends JpaRepository<ShippingArticle, UUID> {

}
