package com.akg.catalog.service.impl;

import com.akg.catalog.dto.CategoryAttributeResponseDTO;
import com.akg.catalog.dto.CommonRequestDTO;
import com.akg.catalog.entity.Attribute;
import com.akg.catalog.entity.CategoryAttribute;
import com.akg.catalog.exception.EntityDoesNotExistException;
import com.akg.catalog.repository.AttributeRepository;
import com.akg.catalog.repository.CategoryAttributeRepository;
import com.akg.catalog.service.IAttributeService;
import com.akg.catalog.transformer.CatalogTransformer;
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

    @Autowired
    CatalogTransformer catalogTransformer;

    @Transactional
    public void mapAttributeWithCategory(Attribute attribute, CommonRequestDTO commonRequestDTO) {
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        categoryAttribute.setCategoryId(commonRequestDTO.getCategoryId());
        categoryAttribute.setAttributeId(attribute.getAttributeId());
        categoryAttribute.setAttributeName(attribute.getAttributeName());
        categoryAttribute.setAttributeValue(commonRequestDTO.getValue());
        categoryAttribute.setCreatedBy("Admin");
        categoryAttribute.setCreatedOn(new Date());
        categoryAttribute.setModifiedBy("Admin");
        categoryAttribute.setModifiedOn(new Date());

        categoryAttributeRepository.save(categoryAttribute);
    }

    @Transactional
    public Attribute createAttribute(CommonRequestDTO commonRequestDTO) {
        Attribute attribute = new Attribute();
        attribute.setAttributeName(commonRequestDTO.getName());
        attribute.setAttributeDescription(commonRequestDTO.getDescription());
        attribute.setCreatedBy("Admin");
        attribute.setCreatedOn(new Date());
        attribute.setModifiedBy("Admin");
        attribute.setModifiedOn(new Date());

        return attributeRepository.save(attribute);
    }

    @Override
    public List<CategoryAttributeResponseDTO> getCategoryAttributes(int categoryId) {
        List<CategoryAttribute> categoryAttributes = categoryAttributeRepository.findByCategoryId(categoryId);
        if (CollectionUtils.isEmpty(categoryAttributes)) {
            throw new EntityDoesNotExistException(String.format("Attributes Not Present for category: %s", categoryId));
        }
        List<CategoryAttributeResponseDTO> categoryAttributeResponse = catalogTransformer.prepareCategoryAttributeResponse(categoryAttributes);
        return categoryAttributeResponse;
    }
}
