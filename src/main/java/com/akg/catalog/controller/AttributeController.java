package com.akg.catalog.controller;

import com.akg.catalog.dto.RequestDTO;
import com.akg.catalog.dto.ResponseDTO;
import com.akg.catalog.exception.ExceptionHandler;
import com.akg.catalog.service.IAttributeService;
import com.akg.catalog.validator.CatalogRequestsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    private static Logger LOGGER = LoggerFactory.getLogger(AttributeController.class);

    @Autowired
    IAttributeService attributeService;

    @Autowired
    CatalogRequestsValidator catalogRequestsValidator;

    @Autowired
    ExceptionHandler exceptionHandler;

    @PostMapping(produces = "application/json", name = "Endpoint for Creating a new Attribute")
    public @ResponseBody
    ResponseEntity createAttribute(@RequestBody RequestDTO requestDTO) throws ValidationException {

        ResponseDTO responseDTO = null;
        try {
            catalogRequestsValidator.validateCreateAttributeRequest(requestDTO);
            attributeService.createAttribute(requestDTO);
        } catch (Exception ex) {
            LOGGER.error("Exception happened while creating category with name: {}", requestDTO.getName(), ex);
            responseDTO = exceptionHandler.mapAndThrow(ex);
            return new ResponseEntity<>(responseDTO, HttpStatus.valueOf(responseDTO.getCode()));
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
