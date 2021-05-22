package com.akg.catalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestDTO {

    private String name;
    private String value;
    private String description;
    private int categoryId;
    private List<CategoryAttributeResponseDTO> categoryAttributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
