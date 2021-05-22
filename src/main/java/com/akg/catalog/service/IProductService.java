package com.akg.catalog.service;

import com.akg.catalog.dto.ProductResponseDTO;
import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.entity.Category;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface IProductService {

    ProductResponseDTO linkAndCreateProduct(Category category, RequestDTO requestDTO) throws IOException;

    ProductResponseDTO getProduct(int productId) throws JsonProcessingException;
}
