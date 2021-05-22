package com.akg.catalog.service;

import com.akg.catalog.dto.CategoryAttributeResponseDTO;
import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.entity.Attribute;

import java.util.List;

public interface IAttributeService {

    Attribute createAttribute(RequestDTO requestDTO);

    void mapAttributeWithCategory(Attribute attribute, RequestDTO requestDTO);

    List<CategoryAttributeResponseDTO> getCategoryAttributes(int categoryId);
}
