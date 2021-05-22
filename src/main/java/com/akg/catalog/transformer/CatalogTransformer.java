package com.akg.catalog.transformer;

import com.akg.catalog.dto.CategoryAttributeResponseDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.entity.CategoryAttribute;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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

    public List<CategoryAttribute> convertToCategoryAttributesEntity(List<CategoryAttributeResponseDTO> categoryAttributesList, Category category) {

        List<CategoryAttribute> categoryAttributes = new ArrayList<>();
        for (CategoryAttributeResponseDTO categoryAttributeDTO : categoryAttributesList) {
/*
            boolean updated = false;
*/
            for (CategoryAttribute categoryAttribute : category.getCategoryAttributeList()) {
                if (categoryAttribute.getAttributeId() == categoryAttributeDTO.getAttributeId()) {
                    categoryAttribute.setAttributeValue(categoryAttributeDTO.getAttributeValue());
                    categoryAttribute.setModifiedBy("Admin");
                    categoryAttribute.setModifiedOn(new Date());
                    categoryAttributes.add(categoryAttribute);
/*
                    updated = true;
*/
                    break;
                }
            }
/*            if (!updated) {
                CategoryAttribute categoryAttribute = new CategoryAttribute();
                BeanUtils.copyProperties(categoryAttributeDTO, categoryAttribute);
                categoryAttribute.setModifiedBy("Admin");
                categoryAttribute.setModifiedOn(new Date());
            }*/
        }
        return categoryAttributes;
    }
}
