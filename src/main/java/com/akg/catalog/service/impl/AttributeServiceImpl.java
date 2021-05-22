package com.akg.catalog.service.impl;

import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.entity.Attribute;
import com.akg.catalog.entity.CategoryAttribute;
import com.akg.catalog.exception.EntityDoesNotExistException;
import com.akg.catalog.repository.AttributeRepository;
import com.akg.catalog.repository.CategoryAttributeRepository;
import com.akg.catalog.service.IAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AttributeServiceImpl implements IAttributeService {

    @Autowired
    AttributeRepository attributeRepository;

    @Autowired
    CategoryAttributeRepository categoryAttributeRepository;

    @Transactional
    public Attribute createAttribute(RequestDTO requestDTO) {
        Attribute attribute = new Attribute();
        attribute.setAttributeName(requestDTO.getName());
        attribute.setAttributeDescription(requestDTO.getDescription());
        attribute.setCreatedBy("Admin");
        attribute.setCreatedOn(new Date());
        attribute.setModifiedBy("Admin");
        attribute.setModifiedOn(new Date());

        return attributeRepository.save(attribute);
    }

    @Transactional
    public void mapAttributeWithCategory(Attribute attribute, RequestDTO requestDTO) {
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        categoryAttribute.setAttributeId(attribute.getAttributeId());
        categoryAttribute.setAttributeName(attribute.getAttributeName());
        categoryAttribute.setAttributeValue(requestDTO.getValue());
        categoryAttribute.setCreatedBy("Admin");
        categoryAttribute.setCreatedOn(new Date());
        categoryAttribute.setModifiedBy("Admin");
        categoryAttribute.setModifiedOn(new Date());

        categoryAttributeRepository.save(categoryAttribute);
    }

    @Override
    public List<CategoryAttribute> getCategoryAttributes(String categoryId) {
        List<CategoryAttribute> categoryAttribute = categoryAttributeRepository.findByCategoryId(categoryId);
        if (CollectionUtils.isEmpty(categoryAttribute)) {
            throw new EntityDoesNotExistException(String.format("Attributes Not Present for category: %s", categoryId));
        }
        return categoryAttribute;
    }
}
