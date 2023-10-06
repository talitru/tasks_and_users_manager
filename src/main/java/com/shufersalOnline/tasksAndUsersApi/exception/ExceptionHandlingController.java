package com.shufersalOnline.tasksAndUsersApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleException(RuntimeException exception) {

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", exception.getMessage());

        HttpStatus status = determineHttpStatus(exception);

        return ResponseEntity.status(status).body(errorResponse);
    }

    private HttpStatus determineHttpStatus(RuntimeException exception) {

        if (exception instanceof ResourceNotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (exception instanceof AuthorizationException) {
            return HttpStatus.FORBIDDEN;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
