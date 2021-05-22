package com.akg.catalog.service;

import com.akg.catalog.dto.ProductResponseDTO;
import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.entity.Product;

public interface IProductService {

    ProductResponseDTO linkAndCreateProduct(Category category, RequestDTO requestDTO);

    ProductResponseDTO getProduct(int productId);
}
