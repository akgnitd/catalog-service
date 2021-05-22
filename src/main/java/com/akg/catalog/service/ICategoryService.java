package com.akg.catalog.service;

import com.akg.catalog.dto.CategoryResponseDTO;
import com.akg.catalog.dto.RequestDTO;

public interface ICategoryService {

    CategoryResponseDTO createCategory(RequestDTO requestDTO);
}
