package com.project.minio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.minio.common.ValidationErrorResponse;

@ControllerAdvice
@RestController
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ValidationException.class)
	  public ResponseEntity<ValidationErrorResponse> validationFailure(
	      Exception ex, WebRequest request) {
	    return new ResponseEntity<>(((ValidationException) ex).getResponse(), HttpStatus.BAD_REQUEST);
	  }
}
