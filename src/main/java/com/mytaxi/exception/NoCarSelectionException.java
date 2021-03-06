package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * PROJECT   : server-applicant-test-18
 * PACKAGE   : com.mytaxi.exception
 * USER      : sean
 * DATE      : 02-Tue-Oct-2018
 * TIME      : 18:17
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */


@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No car selection made.")
public class NoCarSelectionException extends Exception {
    static final long serialVersionUID = -3389416993124229948L;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public NoCarSelectionException(String message) {
        super(message);
    }
}
