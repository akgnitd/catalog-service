package com.akg.catalog.exception;

import com.akg.catalog.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler {

    public ResponseDTO mapAndThrow(Exception e) {

        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = "INTERNAL_SERVER_ERROR";
        ResponseDTO responseDTO = null;

        if (e.getClass().isAssignableFrom(ValidationException.class)) {
            code = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.toString();
            responseDTO = new ResponseDTO(message, code, e.getMessage());
        } else if (e.getClass().isAssignableFrom(EntityDoesNotExistException.class)) {
            code = HttpStatus.NOT_FOUND.value();
            message = HttpStatus.NOT_FOUND.toString();
            responseDTO = new ResponseDTO(message, code, e.getMessage());
        } else {
            responseDTO = new ResponseDTO(message, code, e.getMessage());
        }
        return responseDTO;
    }
}
