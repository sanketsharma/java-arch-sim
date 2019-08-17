package com.ssharma.Exceptions;

public class InvalidMemoryAddressException extends RuntimeException{
    public InvalidMemoryAddressException(String message){
        super(message);
    }
}
