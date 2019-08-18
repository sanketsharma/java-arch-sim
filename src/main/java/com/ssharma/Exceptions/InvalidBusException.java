package com.ssharma.Exceptions;

public class InvalidBusException extends RuntimeException {
    public InvalidBusException(String message){
        super(message);
    }
}
