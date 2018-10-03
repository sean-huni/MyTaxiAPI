package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.exception
 * USER      : sean
 * DATE      : 02-Tue-Oct-2018
 * TIME      : 11:25
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Driver must be online to select a car.")
public class DriverNotOnlineException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DriverNotOnlineException(String message) {
        super(message);
    }
}
