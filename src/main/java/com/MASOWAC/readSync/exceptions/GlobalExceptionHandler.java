package com.MASOWAC.readSync.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

  
//Handle ReaderNotFoundException

//     Handle book not found exception

    @ExceptionHandler(NoBookFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoBookFoundException(NoBookFoundException ex, HttpServletRequest request) {
        Map<String, Object> response = new LinkedHashMap<>(); // Preserves order
        response.put("status", "failure");
        response.put("statusCode", HttpStatus.NOT_FOUND.value());
        response.put("message", ex.getMessage());
        response.put("path", request.getRequestURI());
        response.put("timestamp", System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    // Handle 404 errors (Invalid URL)

}

