package com.ssharma.Exceptions;

public class InvalidAddressException extends RuntimeException{
    public InvalidAddressException(String message){
        super(message);
    }
}
