package com.akg.catalog.service.impl;

import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.repository.CategoryRepository;
import com.akg.catalog.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public void createCategory(RequestDTO requestDTO) {

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
