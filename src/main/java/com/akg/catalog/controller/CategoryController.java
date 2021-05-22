package com.akg.catalog.controller;

import com.akg.catalog.dto.CreateCatalogRequestDTO;
import com.akg.catalog.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private static Logger LOGGER = LoggerFactory.getLogger(CatalogController.class);

    @PostMapping(path = "/", produces = "application/json", name = "Endpoint for Creating a new Category")
    public @ResponseBody
    ResponseEntity createCatalog(@RequestBody CreateCatalogRequestDTO requestDTO) {

        ResponseDTO responseDTO = new ResponseDTO("Health Check API", HttpStatus.OK.value(), "Catalog Service is up and running");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
