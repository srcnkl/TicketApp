package com.ticketapp.exception;

public class TimeoutException extends RuntimeException {
    public TimeoutException(){
        super(ExceptionCode.OperationHasTimeOut);
    }
}
