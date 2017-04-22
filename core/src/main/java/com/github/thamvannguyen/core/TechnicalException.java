package com.github.thamvannguyen.core;

/**
 * Created by huynhduychuong on Oct 3, 2016.
 */
public class TechnicalException extends RuntimeException {

    /**
     *
     */
    public TechnicalException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public TechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public TechnicalException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public TechnicalException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
