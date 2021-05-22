package com.akg.catalog.controller;

import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.dto.ResponseDTO;
import com.akg.catalog.entity.Attribute;
import com.akg.catalog.entity.CategoryAttribute;
import com.akg.catalog.exception.ExceptionHandler;
import com.akg.catalog.service.IAttributeService;
import com.akg.catalog.service.ICategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    private static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IAttributeService attributeService;

    @Autowired
    CatalogRequestsValidator catalogRequestsValidator;

    @Autowired
    ExceptionHandler exceptionHandler;

    @PostMapping(produces = "application/json", name = "Endpoint for Creating a new Category")
    public @ResponseBody
    ResponseEntity createCategory(@RequestBody RequestDTO requestDTO) throws ValidationException {

        ResponseDTO responseDTO = null;
        try {
            catalogRequestsValidator.validateCreateCategoryRequest(requestDTO);
            categoryService.createCategory(requestDTO);
        } catch (Exception ex) {
            LOGGER.error("Exception happened while creating category with name: {}", requestDTO.getName(), ex);
            responseDTO = exceptionHandler.mapAndThrow(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getCode()));
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping(path = "/attribute", produces = "application/json", name = "Endpoint for Creating a new Category Attribute")
    public @ResponseBody
    ResponseEntity createCategoryAttribute(@RequestBody RequestDTO requestDTO) throws ValidationException {

        ResponseDTO responseDTO = null;
        try {
            Attribute attribute = catalogRequestsValidator.validateCreateCategoryAttributeRequest(requestDTO);
            if (null == attribute) {
                attribute = attributeService.createAttribute(requestDTO);
            }
            attributeService.mapAttributeWithCategory(attribute, requestDTO);
        } catch (Exception ex) {
            LOGGER.error("Exception happened while creating category attribute with name: {}", requestDTO.getName(), ex);
            responseDTO = exceptionHandler.mapAndThrow(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getCode()));
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/{categoryId}/attribute", produces = "application/json", name = "Endpoint for Health Check")
    public @ResponseBody
    ResponseEntity getCategoryAttributes(@PathVariable("categoryId") String categoryId) {
        ResponseDTO responseDTO;
        List<CategoryAttribute> categoryAttribute;
        try {
            categoryAttribute = attributeService.getCategoryAttributes(categoryId);
        } catch (Exception ex) {
            LOGGER.error("Exception happened while fetching attributes for categoryId: {}", categoryId, ex);
            responseDTO = exceptionHandler.mapAndThrow(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getCode()));
        }
        return new ResponseEntity<>(categoryAttribute, HttpStatus.OK);
    }

}
