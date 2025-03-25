package com.MASOWAC.readSync.exceptions;

import com.MASOWAC.readSync.dto.ApiResponse;
import com.MASOWAC.readSync.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleGeneralException(Exception ex, HttpServletRequest request) {
        return ResponseUtil.error(List.of(ex.getMessage()), "An unexpected error occurred", 1001, request.getRequestURI());
    }
//Handle ReaderNotFoundException
    @ExceptionHandler(ReaderNotFoundException.class)
    public ApiResponse<Object> handleReaderNotFoundException(ReaderNotFoundException ex, HttpServletRequest request) {
        return ResponseUtil.error(List.of(ex.getMessage()), "Resource not found", 404, request.getRequestURI());
    }

    // Handle resource not found exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<Object> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        return ResponseUtil.error(List.of(ex.getMessage()), "Reader not found", 404, request.getRequestURI());
    }

    // Handle response not found exceptions
    @ExceptionHandler(ResponseNotFoundException.class)
    public ApiResponse<Object> handleResponseNotFoundException(ResponseNotFoundException ex, HttpServletRequest request) {
        return ResponseUtil.error(List.of(ex.getMessage()), "Response data not found", 204, request.getRequestURI());
    }

    // **Handle validation errors (MethodArgumentNotValidException)**
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseUtil.error(errors, "Validation error", 400, request.getRequestURI());
    }

    // **Handle ConstraintViolationException (for @NotNull, @Size, etc.)**
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());

        return ResponseUtil.error(errors, "Validation error", 400, request.getRequestURI());
    }
}
