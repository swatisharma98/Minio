package com.project.minio.common;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private int status = HttpStatus.BAD_REQUEST.value();
    private String error = HttpStatus.BAD_REQUEST.name();
    private List<ValidationError> message;

    public ValidationErrorResponse() {
        this.message = new ArrayList<>();
    }

    public List<ValidationError> getMessage() {
        return message;
    }

    public void setMessage(List<ValidationError> message) {
        this.message = message;
    }

    public ValidationErrorResponse add(ValidationError error) {
        if (error != null) {
            this.message.add(error);
        }
        return this;
    }

    public boolean hasErrors() {
        return !this.message.isEmpty();
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

}
