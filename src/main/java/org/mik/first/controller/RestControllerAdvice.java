package org.mik.first.controller;

import lombok.extern.log4j.Log4j2;
import org.mik.first.exception.BadParameterException;
import org.mik.first.exception.Message;
import org.mik.first.exception.NotImplementedException;
import org.mik.first.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public Message resourceNotfund(ResourceNotFoundException e, WebRequest r) {
        log.warn("Resource not found:%s, req:%s".formatted(e.getMessage(), r));
        return new Message(HttpStatus.NOT_FOUND.value(), "not found",
                e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BadParameterException.class)
    public Message badParameter(BadParameterException e, WebRequest r) {
        log.warn("Bad parameter:%s, req:%s".formatted(e.getMessage(), r));
        return new Message(HttpStatus.BAD_REQUEST.value(), "bad request",
                e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotImplementedException.class)
    public Message notImplemented(NotImplementedException e, WebRequest r) {
        log.warn("Not implemented:%s, req:%s".formatted(e.getMessage(), r));
        return new Message(HttpStatus.NOT_IMPLEMENTED.value(), "not implemented",
                e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public Message serverError(Exception e, WebRequest r) {
        log.error("server error:%s, req:%s".formatted(e.getMessage(), r));
        return new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), "server error",
                e.getMessage(), LocalDateTime.now());
    }

}
