package com.akg.catalog.service;

import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.entity.Product;

public interface IProductService {

    void linkAndCreateProduct(Category category, RequestDTO requestDTO);

    Product getProduct(String productId);
}
