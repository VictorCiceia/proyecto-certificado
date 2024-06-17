package com.project.certified.controller;

import com.project.certified.dto.ExceptionDto;
import com.project.certified.exception.FailedLoanException;
import com.project.certified.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto resourceNotFoundException(final ResourceNotFoundException e) {
        logger.warn(e.getMessage());
        return new ExceptionDto(new String[]{e.getReason()}, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString());

    }

    @ResponseBody
    @ExceptionHandler(FailedLoanException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto failedLoanException(final FailedLoanException e) {
        logger.warn(e.getMessage());
        return new ExceptionDto(new String[]{e.getReason()}, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto exception(final Exception e) {
        logger.error(e.getMessage());
        return new ExceptionDto(new String[]{"Ah ocurrido un error, contacte con el servicio técnico"}, HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }

}
