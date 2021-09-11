package com.project.minio.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PreexistingEntityException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String ERROR_CODE_PREFIX = "app.minio.already.exist.";

    public PreexistingEntityException() {
        super();
        this.setStackTrace(null);
    }

    public PreexistingEntityException(String s, MessageSource messageSource) {
        super(messageSource.getMessage(ERROR_CODE_PREFIX + s, null, LocaleContextHolder.getLocale()));
        this.setStackTrace(new StackTraceElement[]{});
    }
}
