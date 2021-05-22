package com.akg.catalog.service.impl;

import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.entity.Product;
import com.akg.catalog.exception.EntityDoesNotExistException;
import com.akg.catalog.repository.ProductRepository;
import com.akg.catalog.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Transactional
    public void linkAndCreateProduct(Category category, RequestDTO requestDTO) {
        Product product = new Product();
        product.setProductName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setCategoryId(requestDTO.getCategoryId());
        product.setCategoryName(requestDTO.getName());

        product.setCreatedBy("Admin");
        product.setCreatedOn(new Date());
        product.setModifiedBy("Admin");
        product.setModifiedOn(new Date());
        productRepository.save(product);
    }

    public Product getProduct(String productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (null == product) {
            throw new EntityDoesNotExistException(String.format("Product Not Found with productId: %s", productId));
        }
        return product;
    }
}
