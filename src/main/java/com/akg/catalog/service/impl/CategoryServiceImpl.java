package com.akg.catalog.service.impl;

import com.akg.catalog.dto.CreateCategoryRequestDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.repository.CategoryRepository;
import com.akg.catalog.service.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public void createCategory(CreateCategoryRequestDTO requestDTO) {

        Category category = new Category();
        category.setCategoryName(requestDTO.getName());
        category.setCategoryDescription(requestDTO.getDescription());
        category.setCreatedBy("Admin");
        category.setCreatedOn(new Date());
        category.setModifiedBy("Admin");
        category.setModifiedOn(new Date());

        categoryRepository.save(category);
    }
}
