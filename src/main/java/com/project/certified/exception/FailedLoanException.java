package com.project.certified.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FailedLoanException extends ResponseStatusException {

    public FailedLoanException(final String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

}
