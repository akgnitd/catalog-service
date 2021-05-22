package com.akg.catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseDTO {

    private String message;
    private int code;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object details;

    public ResponseDTO(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ResponseDTO(String message, int code, Object details) {
        this.message = message;
        this.code = code;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
