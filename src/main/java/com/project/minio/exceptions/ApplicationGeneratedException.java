package com.project.minio.exceptions;

public class ApplicationGeneratedException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String ERROR_CODE_PREFIX = "app.minio.error.";

    public ApplicationGeneratedException(String s) {
        super(ERROR_CODE_PREFIX + s);
    }
}
