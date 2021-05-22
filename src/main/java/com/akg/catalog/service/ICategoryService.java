package com.akg.catalog.service;

import com.akg.catalog.dto.CreateCategoryRequestDTO;

public interface ICategoryService {

    void createCategory(CreateCategoryRequestDTO requestDTO);
}
