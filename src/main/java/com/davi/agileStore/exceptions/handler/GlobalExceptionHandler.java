package com.davi.agileStore.exceptions.handler;

import com.davi.agileStore.exceptions.domains.ResourceNotFoundException;
import com.davi.agileStore.exceptions.standard.StandardErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(com.fasterxml.jackson.core.JsonProcessingException.class)
    public ResponseEntity<StandardErrorMessage> handleJsonParseErrors(JsonProcessingException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardErrorMessage err = new StandardErrorMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setErrors(Collections.singletonList(e.getOriginalMessage()));
        err.setMessage("Invalid JSON format");
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardErrorMessage> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardErrorMessage err = new StandardErrorMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorMessage> handleValidationErrors(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        StandardErrorMessage err = new StandardErrorMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setMessage("Validation error");
        err.setErrors(errors);
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
