package com.akg.catalog.repository;

import com.akg.catalog.entity.Attribute;
import com.akg.catalog.entity.CategoryAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, String> {

    Attribute findByAttributeName(String attributeName);
}
