package com.project.minio.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.minio.common.ValidationError;
import com.project.minio.common.ValidationErrorResponse;

/**
 *
 * @author harsh.srivastava
 */
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ERROR_CODE_PREFIX = "app.minio.service.unavailable.";
	private ValidationErrorResponse response = new ValidationErrorResponse();

	public ServiceUnavailableException() {
		super();
		this.setStackTrace(null);
	}

	public ServiceUnavailableException(String s, MessageSource messageSource) {
		super(messageSource.getMessage(ERROR_CODE_PREFIX + s, null, LocaleContextHolder.getLocale()));
		this.setStackTrace(new StackTraceElement[] {});
		this.response.add(new ValidationError(messageSource.getMessage(ERROR_CODE_PREFIX + s, null,
				ERROR_CODE_PREFIX + s, LocaleContextHolder.getLocale()), null, null, null));
	}

	public ValidationErrorResponse getResponse() {
		response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		response.setError(HttpStatus.SERVICE_UNAVAILABLE.name());
		return response;
	}
}
