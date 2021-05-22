package com.akg.catalog.repository;

import com.akg.catalog.entity.CategoryAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryAttributeRepository extends JpaRepository<CategoryAttribute, String> {

    List<CategoryAttribute> findByCategoryId(String categoryId);

    CategoryAttribute findByAttributeName(String attributeName);

}
