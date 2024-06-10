package com.project.certified.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {
    public ResourceNotFoundException(final String resource, final String key, final String value) {
        super(HttpStatus.NOT_FOUND, "Recurso " + resource + " no fue encontrado por " + key + " " + value);
    }
}
