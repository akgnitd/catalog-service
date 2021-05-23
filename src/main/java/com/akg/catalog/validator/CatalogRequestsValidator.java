package com.akg.catalog.validator;

import com.akg.catalog.dto.CommonRequestDTO;
import com.akg.catalog.dto.ProductRequestDTO;
import com.akg.catalog.entity.Attribute;
import com.akg.catalog.entity.Category;
import com.akg.catalog.entity.CategoryAttribute;
import com.akg.catalog.exception.ConflictException;
import com.akg.catalog.exception.EntityDoesNotExistException;
import com.akg.catalog.repository.AttributeRepository;
import com.akg.catalog.repository.CategoryAttributeRepository;
import com.akg.catalog.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.ValidationException;

@Component
public class CatalogRequestsValidator {

    private static Logger LOGGER = LoggerFactory.getLogger(CatalogRequestsValidator.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    CategoryAttributeRepository categoryAttributeRepository;

    public void validateCreateCategoryRequest(CommonRequestDTO commonRequestDTO) throws ValidationException {

        if (!StringUtils.hasText(commonRequestDTO.getName())) {
            throw new ValidationException("Category Name cannot be null or blank");
        }
        Category category = categoryRepository.findByCategoryName(commonRequestDTO.getName());
        if (null != category) {
            throw new ConflictException(String.format("Category: [%s] is already present", commonRequestDTO.getName()));
        }
    }

    public void validateCreateAttributeRequest(CommonRequestDTO commonRequestDTO) throws ValidationException {
        if (!StringUtils.hasText(commonRequestDTO.getName())) {
            throw new ValidationException("Attribute Name cannot be null or blank");
        }
        Attribute attribute = attributeRepository.findByAttributeName(commonRequestDTO.getName());
        if (null != attribute) {
            throw new ConflictException(String.format("Attribute: [%s] is already present", commonRequestDTO.getName()));
        }
    }

    public Attribute validateCreateCategoryAttributeRequest(CommonRequestDTO commonRequestDTO) throws ValidationException {
        if (!StringUtils.hasText(commonRequestDTO.getName())) {
            throw new ValidationException("Attribute Name cannot be null or blank");
        }
        CategoryAttribute categoryAttribute = categoryAttributeRepository.findByAttributeName(commonRequestDTO.getName());
        if (null != categoryAttribute) {
            throw new ConflictException(String.format("Attribute: [%s] is already present", commonRequestDTO.getName()));
        }
        Attribute attribute = attributeRepository.findByAttributeName(commonRequestDTO.getName());
        return attribute;
    }

    public Category validateCreateProductRequest(ProductRequestDTO commonRequestDTO) throws ValidationException {
        if (!StringUtils.hasText(commonRequestDTO.getName())) {
            throw new ValidationException("Product Name cannot be null or blank");
        }
        if (commonRequestDTO.getCategoryId() == 0) {
            throw new ValidationException("Category ID cannot be null or blank");
        }
        Category category = categoryRepository.findById(commonRequestDTO.getCategoryId()).orElse(null);
        if (null == category) {
            throw new EntityDoesNotExistException(String.format("To be linked Category: [%s] not found", commonRequestDTO.getName()));
        }
        return category;
    }
}
