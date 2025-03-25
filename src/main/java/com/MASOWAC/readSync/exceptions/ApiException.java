package com.MASOWAC.readSync.exceptions;

import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final int statusCode;
    private final String status;
    private final ZonedDateTime timestamp;


    public ApiException(String message, HttpStatus httpStatus, int statusCode,String status, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
