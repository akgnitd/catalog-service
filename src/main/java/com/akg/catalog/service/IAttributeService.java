package com.akg.catalog.service;

import com.akg.catalog.dto.CategoryAttributeResponseDTO;
import com.akg.catalog.dto.CommonRequestDTO;
import com.akg.catalog.entity.Attribute;

import java.util.List;

public interface IAttributeService {

    Attribute createAttribute(CommonRequestDTO commonRequestDTO);

    void mapAttributeWithCategory(Attribute attribute, CommonRequestDTO commonRequestDTO);

    List<CategoryAttributeResponseDTO> getCategoryAttributes(int categoryId);
}
