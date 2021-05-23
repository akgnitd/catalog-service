package com.akg.catalog.service;

import com.akg.catalog.dto.CategoryResponseDTO;
import com.akg.catalog.dto.CommonRequestDTO;

public interface ICategoryService {

    CategoryResponseDTO createCategory(CommonRequestDTO commonRequestDTO);
}
