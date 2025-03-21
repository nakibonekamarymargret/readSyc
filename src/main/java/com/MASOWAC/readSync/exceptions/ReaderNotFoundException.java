package com.MASOWAC.readSync.exceptions;

public class ReaderNotFoundException extends RuntimeException{
    public ReaderNotFoundException(String message){
        super(message);
    }
    public ReaderNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

}
