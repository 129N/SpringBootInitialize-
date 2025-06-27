package org.mik.first.controller;

import lombok.extern.log4j.Log4j2;
import org.mik.first.exception.BadParameterException;
import org.mik.first.exception.Message;
import org.mik.first.exception.NotImplementedException;
import org.mik.first.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;


import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)

    public ResponseEntity<Message> resourceNotFound(ResourceNotFoundException e, WebRequest request) {
        log.warn("Resource not found: %s, req:%s".formatted(e.getMessage(), request));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Message(HttpStatus.NOT_FOUND.value(),
                        "not found", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(BadParameterException.class)
    public ResponseEntity<Message> badParameter(BadParameterException e, WebRequest request) {
        log.warn("Bad parameter: %s, req:%s".formatted(e.getMessage(), request));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Message(HttpStatus.BAD_REQUEST.value(),
                        "Bad request", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<Message> notImplemented(NotImplementedException e, WebRequest req) {
        log.error("Function not implemented:%s, req:%s".formatted(e.getMessage(), req));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new Message(HttpStatus.NOT_ACCEPTABLE.value(),
                        "not implemented", "", LocalDateTime.now()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Message> noResourceFound(NoResourceFoundException e, WebRequest request) {
        log.warn("Resource not found: %s, req:%s".formatted(e.getMessage(), request));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Message(HttpStatus.NOT_FOUND.value(),
                        "not found", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> serverError(Exception e, WebRequest req) {
        log.error("Server error: %s, req:%s".formatted(e.getMessage(), req));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "server error", e.getMessage(), LocalDateTime.now()));
    }


}
