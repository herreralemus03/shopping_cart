package com.hlstudios.orders.services.datasource.article;

import com.hlstudios.orders.entites.ShippingArticle;
import com.hlstudios.orders.services.datasource.BaseDataSourceService;

import java.util.Collection;
import java.util.List;


public interface ShippingArticleService extends BaseDataSourceService<ShippingArticle> {

    List<ShippingArticle> addAll(Collection<ShippingArticle> shippingArticles);

}
