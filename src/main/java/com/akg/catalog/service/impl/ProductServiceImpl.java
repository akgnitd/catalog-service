package com.akg.catalog.service.impl;

import com.akg.catalog.dto.CategoryAttributeResponseDTO;
import com.akg.catalog.dto.ProductResponseDTO;
import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.entity.Product;
import com.akg.catalog.exception.EntityDoesNotExistException;
import com.akg.catalog.repository.ProductRepository;
import com.akg.catalog.service.IProductService;
import com.akg.catalog.transformer.CatalogTransformer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CatalogTransformer catalogTransformer;

    @Transactional
    public ProductResponseDTO linkAndCreateProduct(Category category, RequestDTO requestDTO) {
        Product product = new Product();
        product.setProductName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setCategory(category);
        product.setCategoryName(category.getCategoryName());

        product.setCreatedBy("Admin");
        product.setCreatedOn(new Date());
        product.setModifiedBy("Admin");
        product.setModifiedOn(new Date());

        product = productRepository.save(product);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties(product, productResponseDTO);
        return productResponseDTO;
    }

    public ProductResponseDTO getProduct(int productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (null == product) {
            throw new EntityDoesNotExistException(String.format("Product Not Found with productId: %s", productId));
        }
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        BeanUtils.copyProperties(product, productResponseDTO);

        if (null != product.getCategory()) {
            List<CategoryAttributeResponseDTO> categoryAttributeResponse = catalogTransformer.prepareCategoryAttributeResponse(product.getCategory().getCategoryAttributeList());
            productResponseDTO.setCategoryAttributes(categoryAttributeResponse);
        }
        return productResponseDTO;
    }
}
