package com.akg.catalog.validator;

import com.akg.catalog.dto.CreateCategoryRequestDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.exception.ConflictException;
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


    public void validateCreateCategoryRequest(CreateCategoryRequestDTO requestDTO) throws ValidationException {

        if (!StringUtils.hasText(requestDTO.getName())) {
            throw new ValidationException("Category Name cannot be null or blank");
        }
        Category category = categoryRepository.findByCategoryName(requestDTO.getName());
        if (null != category) {
            throw new ConflictException(String.format("Category: [%s] is already present", requestDTO.getName()));
        }
    }
}
