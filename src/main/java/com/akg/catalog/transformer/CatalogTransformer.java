package com.akg.catalog.transformer;

import com.akg.catalog.dto.CategoryAttributeResponseDTO;
import com.akg.catalog.entity.CategoryAttribute;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatalogTransformer {

    public List<CategoryAttributeResponseDTO> prepareCategoryAttributeResponse(List<CategoryAttribute> categoryAttributes) {
        List<CategoryAttributeResponseDTO> categoryAttributeResponse = new ArrayList<>();

        for (CategoryAttribute categoryAttribute : categoryAttributes) {
            CategoryAttributeResponseDTO categoryAttributeResponseDTO = new CategoryAttributeResponseDTO();
            BeanUtils.copyProperties(categoryAttribute, categoryAttributeResponseDTO);

            categoryAttributeResponse.add(categoryAttributeResponseDTO);
        }
        return categoryAttributeResponse;
    }
}
