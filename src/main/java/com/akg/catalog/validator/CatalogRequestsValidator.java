package com.akg.catalog.validator;

import com.akg.catalog.dto.RequestDTO;
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

    public void validateCreateCategoryRequest(RequestDTO requestDTO) throws ValidationException {

        if (!StringUtils.hasText(requestDTO.getName())) {
            throw new ValidationException("Category Name cannot be null or blank");
        }
        Category category = categoryRepository.findByCategoryName(requestDTO.getName());
        if (null != category) {
            throw new ConflictException(String.format("Category: [%s] is already present", requestDTO.getName()));
        }
    }

    public void validateCreateAttributeRequest(RequestDTO requestDTO) throws ValidationException {
        if (!StringUtils.hasText(requestDTO.getName())) {
            throw new ValidationException("Attribute Name cannot be null or blank");
        }
        Attribute attribute = attributeRepository.findByAttributeName(requestDTO.getName());
        if (null != attribute) {
            throw new ConflictException(String.format("Attribute: [%s] is already present", requestDTO.getName()));
        }
    }

    public Attribute validateCreateCategoryAttributeRequest(RequestDTO requestDTO) throws ValidationException {
        if (!StringUtils.hasText(requestDTO.getName())) {
            throw new ValidationException("Attribute Name cannot be null or blank");
        }
        CategoryAttribute categoryAttribute = categoryAttributeRepository.findByAttributeName(requestDTO.getName());
        if (null != categoryAttribute) {
            throw new ConflictException(String.format("Attribute: [%s] is already present", requestDTO.getName()));
        }
        Attribute attribute = attributeRepository.findByAttributeName(requestDTO.getName());
        return attribute;
    }

    public Category validateCreateProductRequest(RequestDTO requestDTO) throws ValidationException {
        if (!StringUtils.hasText(requestDTO.getName())) {
            throw new ValidationException("Product Name cannot be null or blank");
        }
        if (!StringUtils.hasText(requestDTO.getCategoryId())) {
            throw new ValidationException("Category ID cannot be null or blank");
        }
        Category category = categoryRepository.findById(requestDTO.getCategoryId()).orElse(null);
        if (null == category) {
            throw new EntityDoesNotExistException(String.format("To be linked Category: [%s] not found", requestDTO.getName()));
        }
        return category;
    }
}
