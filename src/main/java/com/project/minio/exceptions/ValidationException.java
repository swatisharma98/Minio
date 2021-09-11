package com.project.minio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.minio.common.ValidationErrorResponse;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ValidationErrorResponse response;

    public ValidationException(ValidationErrorResponse response) {
        super("validationError");
        this.response = response;
        this.setStackTrace(new StackTraceElement[]{});
    }

    public ValidationErrorResponse getResponse() {
        return response;
    }
}
