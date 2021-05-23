package com.akg.catalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRequestDTO extends CommonRequestDTO {

    private int categoryId;
    private List<CategoryAttributeResponseDTO> categoryAttributes;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<CategoryAttributeResponseDTO> getCategoryAttributes() {
        return categoryAttributes;
    }

    public void setCategoryAttributes(List<CategoryAttributeResponseDTO> categoryAttributes) {
        this.categoryAttributes = categoryAttributes;
    }
}
