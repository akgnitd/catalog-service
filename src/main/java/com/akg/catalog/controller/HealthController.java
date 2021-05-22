package com.akg.catalog.controller;

import com.akg.catalog.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private static Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

    @GetMapping(produces = "application/json", name = "Endpoint for Health Check")
    public @ResponseBody
    ResponseEntity getHealthStatus() {
        LOGGER.info("Catalog Service is up and running");
        ResponseDTO responseDTO = new ResponseDTO("Health Check API", HttpStatus.OK.value(), "Catalog Service is up and running");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
