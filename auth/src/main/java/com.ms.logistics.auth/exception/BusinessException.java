package com.ms.logistics.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends Exception {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Constructor.
     *
     * @param message
     * 		Message text
     */
    public BusinessException(String message) {
        this(message, null);
    }

    /**
     * Constructor.
     *
     * @param message
     * 		Message text.
     * @param e
     * 		Exception.
     */
    public BusinessException(String message, Exception e) {
        super(message, e);

        logger.error(this.getMessage());
    }
}
