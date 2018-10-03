package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.exception
 * USER      : sean
 * DATE      : 27-Thu-Sep-2018
 * TIME      : 19:03
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "Car already selected by the same driver.")
public class CarAlreadySelectedException extends Exception {
    static final long serialVersionUID = -3387516993334229948L;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CarAlreadySelectedException(String message) {
        super(message);
    }
}
