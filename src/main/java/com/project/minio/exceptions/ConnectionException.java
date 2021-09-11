package com.project.minio.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author harsh.srivastava
 */
@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
public class ConnectionException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public static final String ERROR_CODE_PREFIX = "app.minio.connection.refused.";

    public ConnectionException() {
        super();
        this.setStackTrace(null);
    }

    public ConnectionException(String s, MessageSource messageSource) {
        super(messageSource.getMessage(ERROR_CODE_PREFIX + s, null, LocaleContextHolder.getLocale()));
        this.setStackTrace(new StackTraceElement[]{});
    }
    
    public ConnectionException(String s, MessageSource messageSource, StackTraceElement[] exceptionMessage) {
        super(messageSource.getMessage(ERROR_CODE_PREFIX + s, null, LocaleContextHolder.getLocale()));
        this.setStackTrace(exceptionMessage);
    }

}
