package com.akg.catalog.service.impl;

import com.akg.catalog.dto.CategoryAttributeResponseDTO;
import com.akg.catalog.dto.ProductRequestDTO;
import com.akg.catalog.dto.ProductResponseDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.entity.CategoryAttribute;
import com.akg.catalog.entity.Product;
import com.akg.catalog.exception.EntityDoesNotExistException;
import com.akg.catalog.repository.CategoryAttributeRepository;
import com.akg.catalog.repository.ProductRepository;
import com.akg.catalog.service.IProductService;
import com.akg.catalog.transformer.CatalogTransformer;
import com.akg.catalog.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CatalogTransformer catalogTransformer;

    @Autowired
    CategoryAttributeRepository categoryAttributeRepository;

    @Transactional
    public ProductResponseDTO linkAndCreateProduct(Category category, ProductRequestDTO commonRequestDTO) throws IOException {
        Product product = new Product();
        product.setProductName(commonRequestDTO.getName());
        product.setDescription(commonRequestDTO.getDescription());
        product.setCategoryName(category.getCategoryName());
        product.setCategoryId(category.getCategoryId());
        product.setCreatedBy("Admin");
        product.setCreatedOn(new Date());
        product.setModifiedBy("Admin");
        product.setModifiedOn(new Date());

        if (!CollectionUtils.isEmpty(commonRequestDTO.getCategoryAttributes())) {
            List<CategoryAttribute> categoryAttributeList = categoryAttributeRepository.findByCategoryId(category.getCategoryId());
            List<CategoryAttribute> categoryAttributes = catalogTransformer.convertToCategoryAttributesEntity(commonRequestDTO.getCategoryAttributes(), categoryAttributeList);
            product.setCategoryAttributes(JsonUtils.objectToJson(categoryAttributes));
        }
        product = productRepository.save(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties(product, productResponseDTO);
        return productResponseDTO;
    }

    public ProductResponseDTO getProduct(int productId) throws JsonProcessingException {
        Product product = productRepository.findById(productId).orElse(null);
        if (null == product) {
            throw new EntityDoesNotExistException(String.format("Product Not Found with productId: %s", productId));
        }
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties(product, productResponseDTO);

        if (StringUtils.hasText(product.getCategoryAttributes())) {
            List<CategoryAttribute> categoryAttributes = Arrays.asList(JsonUtils.jsonToObject(product.getCategoryAttributes(), CategoryAttribute[].class));
            List<CategoryAttributeResponseDTO> categoryAttributeResponse = catalogTransformer.prepareCategoryAttributeResponse(categoryAttributes);
            productResponseDTO.setCategoryAttributes(categoryAttributeResponse);
        }
        return productResponseDTO;
    }
}
