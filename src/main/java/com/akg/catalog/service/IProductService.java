package com.akg.catalog.service;

import com.akg.catalog.dto.ProductRequestDTO;
import com.akg.catalog.dto.ProductResponseDTO;
import com.akg.catalog.entity.Category;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface IProductService {

    ProductResponseDTO linkAndCreateProduct(Category category, ProductRequestDTO commonRequestDTO) throws IOException;

    ProductResponseDTO getProduct(int productId) throws JsonProcessingException;
}
