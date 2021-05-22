package com.akg.catalog.controller;

import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.dto.ResponseDTO;
import com.akg.catalog.entity.Category;
import com.akg.catalog.entity.CategoryAttribute;
import com.akg.catalog.entity.Product;
import com.akg.catalog.exception.ExceptionHandler;
import com.akg.catalog.service.IProductService;
import com.akg.catalog.validator.CatalogRequestsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    IProductService productService;

    @Autowired
    CatalogRequestsValidator catalogRequestsValidator;

    @Autowired
    ExceptionHandler exceptionHandler;

    @PostMapping(produces = "application/json", name = "Endpoint for Creating a new Product")
    public @ResponseBody
    ResponseEntity createProduct(@RequestBody RequestDTO requestDTO) throws ValidationException {

        ResponseDTO responseDTO = null;
        try {
            Category category = catalogRequestsValidator.validateCreateProductRequest(requestDTO);
            productService.linkAndCreateProduct(category, requestDTO);
        } catch (Exception ex) {
            LOGGER.error("Exception happened while creating product with name: {}", requestDTO.getName(), ex);
            responseDTO = exceptionHandler.mapAndThrow(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getCode()));
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}", produces = "application/json", name = "Endpoint for Fetching Product Info by ProductId")
    public @ResponseBody
    ResponseEntity getProductById(@PathVariable("productId") String productId) {
        ResponseDTO responseDTO;
        Product product;
        try {
            product = productService.getProduct(productId);
        } catch (Exception ex) {
            LOGGER.error("Exception happened while fetching product info for productId: {}", productId, ex);
            responseDTO = exceptionHandler.mapAndThrow(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getCode()));
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
