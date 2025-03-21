package com.MASOWAC.readSync.exceptions;


import com.MASOWAC.readSync.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


    //Handle ReaderNotFoundException
    @ExceptionHandler(value = {ReaderNotFoundException.class})
    public ResponseEntity<Object> handleReaderNotFoundException(ReaderNotFoundException e){
        HttpStatus badRequest =  HttpStatus.BAD_REQUEST;
        ApiException apiException  = new ApiException(
                e.getMessage(),
                badRequest,
                badRequest.value(),
                "failure",
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,badRequest);
    }

// Handle book not found exception

    @ExceptionHandler(NoBookFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoBookFoundException(NoBookFoundException ex, HttpServletRequest request) {
        Map<String, Object> response = new LinkedHashMap<>(); // Preserves order
        response.put("status", "failure");
        response.put("statusCode", HttpStatus.NOT_FOUND.value());
        response.put("message", ex.getMessage());
        response.put("path", request.getRequestURI());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        response.put("timestamp", ZonedDateTime.now().format(formatter));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    // Handle 404 errors (Invalid URL)

}
