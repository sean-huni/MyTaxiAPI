package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.exception
 * USER      : sean
 * DATE      : 02-Tue-Oct-2018
 * TIME      : 20:58
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot deselect a car is not assigned to the owning driver.")
public class DeselectionNotAllowedException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DeselectionNotAllowedException(String message) {
        super(message);
    }
}
